package banking.api.controller;

import banking.api.model.*;
import banking.api.repository.EwalletLinkedRepository;
import banking.api.service.CallAPI;
import banking.api.service.CoreBankService;
import banking.api.service.account.AccountService;
import banking.api.service.customer.CustomerService;
import banking.api.service.ewalletlinked.EwalletLinkedService;
import banking.api.service.otp.OTPService;
import banking.api.service.transaction.TransactionService;
import banking.api.service.transactionecom.TransactionEcomService;
import banking.api.service.transactionewallet.TransactionEwalletService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class AuthenController {

    Date date = new Date();

    @Autowired
    OTPService otpService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    EwalletLinkedRepository ewalletLinkedRepository;

    @Autowired
    EwalletLinkedService ewalletLinkedService;

    @Autowired
    TransactionEwalletService transactionEwalletService;

    @Autowired
    CoreBankService coreBankService;

    @Autowired
    TransactionEcomService transactionEcomService;

    @PostMapping("verifyOTP")
    public ResponseEntity<?> verifyOTP(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);
        String cif = jsonObject.getString("cif");
        String otp = jsonObject.getString("otp");
        String account_number = jsonObject.getString("account_number");

        Transaction objTrans = new Transaction();
        objTrans.setTrans_date(date);
        objTrans.setTrans_type("Kiểm tra OTP");

        Map<String, String> response = new HashMap<>();

        if(otpService.otpCheck(cif, otp)){
            objTrans.setStatus("00");
            objTrans.setDescription("Xác thực OTP thành công");
            transactionService.create(objTrans);

            response.put("errCode","00");
            Customer objCustomer = customerService.findByCif(cif);
            Account objAcc = accountService.findAccountByNumber(account_number);

            //Tạo 1 transaction lưu thông tin thực hiện liên kết ví
            Transaction objTrans1 = new Transaction();
            objTrans1.setTrans_date(date);
            objTrans1.setTrans_type("Thực hiện liên kết ví "+objCustomer.getPhone() + "với tài khoản "+objAcc.getAccount_number());
            objTrans1.setStatus("00");
            objTrans1.setDescription("Liên kết thành công");
            transactionService.create(objTrans1);

            //Lưu thông tin liên kết ví vào EwalletLinked

            EwalletLinked objEL = ewalletLinkedRepository.findByAccAndEwallet(objAcc.getAccount_number(),
                    objCustomer.getPhone());
            if(objEL != null){
                objEL.setStatus("00");
                objEL.setLinked_date(date);
                ewalletLinkedService.update(objEL);

            }else{
                objEL.setTrans_id(objTrans1.getTrans_id());
                objEL.setAccount_number(objAcc.getAccount_number());
                objEL.setEwallet_id(objCustomer.getPhone());
                objEL.setLinked_date(date);
                objEL.setStatus("00");
                ewalletLinkedService.create(objEL);
            }
            CallAPI.callAPI(response);

            //Lưu thông tin TransactionEwallet
            TransactionEwallet objTE = new TransactionEwallet();
            objTE.setTrans_id(objTrans1.getTrans_id());
            objTE.setAccount_number(objAcc.getAccount_number());
            objTE.setEwallet_id(objCustomer.getPhone());
            objTE.setFull_name(objCustomer.getFull_name());
            objTE.setId_card(objCustomer.getId_card());
            transactionEwalletService.create(objTE);

        }else{
            objTrans.setStatus("05");
            objTrans.setDescription("Mã OTP không chính xác");
            transactionService.create(objTrans);

            response.put("errCode","05");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("ecommerce/verifyOTP")
    public ResponseEntity<?> ecommerceOTP(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);

        String cif = jsonObject.getString("cif");
        String otp = jsonObject.getString("otp");
        String account_number = jsonObject.getString("account_number");
        float amount = jsonObject.getFloat("amount");
        String description = jsonObject.getString("description");
        String receive_account = jsonObject.getString("receive_account");

        //Lấy thông tin khách hàng để lưu transactionEcom
        Customer objCustomer = customerService.findByCif(cif);

        //Kiểm tra OTP
        Transaction objTrans1 = new Transaction();
        objTrans1.setTrans_date(date);
        objTrans1.setTrans_type("Kiểm tra OTP");

        Map<String, String> response;

        if(otpService.otpCheck(cif,otp)){
            objTrans1.setStatus("00");
            objTrans1.setDescription("Xác thực OTP thành công");
            transactionService.create(objTrans1);

            response = coreBankService.transferMoney(account_number,receive_account,amount,description);

            //Lưu vào TransactionEcommerce
            TransactionEcom objTransEcom = new TransactionEcom();
            objTransEcom.setTrans_id(Integer.parseInt(response.get("trans_id")));
            objTransEcom.setAccount_number(account_number);
            objTransEcom.setFull_name(objCustomer.getFull_name());
            objTransEcom.setId_card(objCustomer.getId_card());

            transactionEcomService.create(objTransEcom);
        }else{
            response = new HashMap<>();
            response.put("errCode", "08");
            response.put("errDesc", "Mã OTP không chính xác");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
