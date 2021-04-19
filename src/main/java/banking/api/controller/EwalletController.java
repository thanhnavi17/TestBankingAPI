package banking.api.controller;

import banking.api.model.*;
import banking.api.repository.EwalletLinkedRepository;
import banking.api.service.account.AccountService;
import banking.api.service.customer.CustomerService;
import banking.api.service.ewalletlinked.EwalletLinkedService;
import banking.api.service.transaction.TransactionService;
import banking.api.service.transactionewallet.TransactionEwalletService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class EwalletController {

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EwalletLinkedRepository ewalletLinkedRepository;

    @Autowired
    TransactionEwalletService transactionEwalletService;

    @Autowired
    EwalletLinkedService ewalletLinkedService;

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody String requestBody){

        JSONObject jsonObject = new JSONObject(requestBody);

        //Tạo transaction để lưu thông tin phiên làm việc
        Transaction objTrans = new Transaction();
        objTrans.setTrans_date(date);
        objTrans.setTrans_type("Kiểm tra thông tin tài khoản");

        Map<String, String> response = new HashMap<>();

        //Lấy thông tin khách hàng trong hệ thống
        Customer objCustomer = customerService.findByIdCard(jsonObject.getString("id_card"));
        if(objCustomer != null){
            //Nếu khách hàng tồn tại thì kiểm tra thông tin tài khoản
            Account objAcc = accountService.findAccountByNumber(jsonObject.getString("account_number"));

            //Nếu account tồn tại và trùng khớp thông tin
            if(objAcc != null){
                if(objCustomer.getFull_name().equals(jsonObject.getString("full_name")) && objCustomer.getPhone().equals(jsonObject.getString("phone"))){
                    objTrans.setStatus("00");
                    objTrans.setDescription("Kiểm tra thành công");
                    transactionService.create(objTrans);


                    response.put("errCode","00");
                    response.put("url","testBankingAPI/login");
                    response.put("account_number", objAcc.getAccount_number());

                }else{
                    objTrans.setStatus("01");
                    objTrans.setDescription("Sai thông tin người dùng");
                    transactionService.create(objTrans);

                    response.put("errCode","01");
                }
            }else{
                objTrans.setStatus("02");
                objTrans.setDescription("Tài khoản không tồn tại");
                transactionService.create(objTrans);

                response.put("errCode","02");
            }
        }else{
            objTrans.setStatus("03");
            objTrans.setDescription("Số chứng minh thư không tồn tại");
            transactionService.create(objTrans);

            response.put("errCode","03");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("deposit")
    public ResponseEntity<?> deposit(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);
        float amount = jsonObject.getFloat("amount");
        String account_number = jsonObject.getString("account_number");
        String ewallet_id = jsonObject.getString("ewallet_id");

        //Lấy thông tin tài khoản liên kết với ví để kiểm tra
        EwalletLinked objEL = ewalletLinkedRepository.findByAccAndEwallet(account_number, ewallet_id);

        Account objAcc = accountService.findAccountByNumber(account_number);
        Customer objCustomer = customerService.findByCif(objAcc.getCif());
        Map<String, String> response = new HashMap<>();

        //Lưu transaction
        Transaction objTrans = new Transaction();
        objTrans.setTrans_date(date);
        objTrans.setTrans_type("Nạp tiền");
        if(objEL.getStatus().equals("00")){
            if(objAcc.getBalance() > amount){
                objAcc.setBalance(objAcc.getBalance() - amount);
                accountService.update(objAcc);

                objTrans.setStatus("00");
                objTrans.setAmount(amount);
                objTrans.setDescription("Nạp tiền vào Momo từ " + objAcc.getAccount_number());
                transactionService.create(objTrans);

                //Lưu transaction ewallet
                TransactionEwallet objTE = new TransactionEwallet();
                objTE.setTrans_id(objTrans.getTrans_id());
                objTE.setAccount_number(account_number);
                objTE.setEwallet_id(ewallet_id);
                objTE.setFull_name(objCustomer.getFull_name());
                objTE.setId_card(objCustomer.getId_card());
                transactionEwalletService.create(objTE);

                response.put("errCode","00");
            }else{
                objTrans.setStatus("09");
                objTrans.setAmount(amount);
                objTrans.setDescription("Số tiền trong tài khoản không đủ");
                transactionService.create(objTrans);

                response.put("errCode", "09");
            }
        }else{
            objTrans.setStatus("11");
            objTrans.setAmount(amount);
            objTrans.setDescription("Ví chưa được liên kết");
            transactionService.create(objTrans);

            //Lưu transaction ewallet
            TransactionEwallet objTE = new TransactionEwallet();
            objTE.setTrans_id(objTrans.getTrans_id());
            objTE.setAccount_number(account_number);
            objTE.setEwallet_id(ewallet_id);
            objTE.setFull_name(objCustomer.getFull_name());
            objTE.setId_card(objCustomer.getId_card());
            transactionEwalletService.create(objTE);
            response.put("errCode","11");
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("withdraw")
    public ResponseEntity<?> withdraw(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);
        float amount = jsonObject.getFloat("amount");
        String account_number = jsonObject.getString("account_number");
        String ewallet_id = jsonObject.getString("ewallet_id");

        //Lấy thông tin tài khoản liên kết với ví để kiểm tra
        EwalletLinked objEL = ewalletLinkedRepository.findByAccAndEwallet(account_number, ewallet_id);

        Account objAcc = accountService.findAccountByNumber(account_number);
        Customer objCustomer = customerService.findByCif(objAcc.getCif());
        Map<String, String> response = new HashMap<>();

        //Lưu transaction
        Transaction objTrans = new Transaction();
        objTrans.setTrans_date(date);
        objTrans.setTrans_type("Rút tiền");
        if(objEL != null && objEL.getStatus().equals("00")){
            objAcc.setBalance(objAcc.getBalance()+ amount);
            accountService.update(objAcc);

            objTrans.setStatus("00");
            objTrans.setAmount(amount);
            objTrans.setDescription("Rút tiền từ Momo về " + objAcc.getAccount_number());
            transactionService.create(objTrans);

            //Lưu transaction ewallet
            TransactionEwallet objTE = new TransactionEwallet();
            objTE.setTrans_id(objTrans.getTrans_id());
            objTE.setAccount_number(account_number);
            objTE.setEwallet_id(ewallet_id);
            objTE.setFull_name(objCustomer.getFull_name());
            objTE.setId_card(objCustomer.getId_card());
            transactionEwalletService.create(objTE);

            response.put("errCode","00");
        }else{
            objTrans.setStatus("11");
            objTrans.setAmount(amount);
            objTrans.setDescription("Ví chưa được liên kết");
            transactionService.create(objTrans);

            //Lưu transaction ewallet
            TransactionEwallet objTE = new TransactionEwallet();
            objTE.setTrans_id(objTrans.getTrans_id());
            objTE.setAccount_number(account_number);
            objTE.setEwallet_id(ewallet_id);
            objTE.setFull_name(objCustomer.getFull_name());
            objTE.setId_card(objCustomer.getId_card());
            transactionEwalletService.create(objTE);
            response.put("errCode","11");
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("confirm")
    public ResponseEntity<?> confirm(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);
        Map<String, String> response = new HashMap<>();

        if(jsonObject.getString("status").equals("00")){
            //Phía ngân hàng thực hiện chuyển tiền cho bên cung cấp dịch vụ ví điện tử(Core banking) ...

            response.put("status", "success");
        }else{
            response.put("status", "failed");
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("unlink")
    public ResponseEntity<?> unlink(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);
        String account_number = jsonObject.getString("account_number");
        String ewallet_id = jsonObject.getString("ewallet_id");
        //Lấy thông tin khách hàng
        Account objAcc = accountService.findAccountByNumber(account_number);
        Customer objCustomer = customerService.findByCif(objAcc.getCif());
        //Lấy thông tin tài khoản đang liên kết với ví
        EwalletLinked objEL = ewalletLinkedRepository.findByAccAndEwallet(account_number, ewallet_id);

        //Lưu transaction hủy liên kết
        Transaction objTrans = new Transaction();
        objTrans.setTrans_type("Hủy liên kết ví");
        objTrans.setTrans_date(date);
        objTrans.setStatus("00");
        objTrans.setDescription("Hủy liên kết thành công");
        transactionService.create(objTrans);

        //Hủy liên kết
        objEL.setStatus("10");

        ewalletLinkedService.update(objEL);

        //Lưu transaction ewallet
        TransactionEwallet objTE = new TransactionEwallet();
        objTE.setTrans_id(objTrans.getTrans_id());
        objTE.setAccount_number(account_number);
        objTE.setEwallet_id(ewallet_id);
        objTE.setFull_name(objCustomer.getFull_name());
        objTE.setId_card(objCustomer.getId_card());
        transactionEwalletService.create(objTE);

        Map<String, String> response = new HashMap<>();
        response.put("errCode","00");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
