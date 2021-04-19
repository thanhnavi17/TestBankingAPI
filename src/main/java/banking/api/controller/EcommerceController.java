package banking.api.controller;

import banking.api.model.Account;
import banking.api.model.Transaction;
import banking.api.service.account.AccountService;
import banking.api.service.transaction.TransactionService;
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
@RequestMapping("api/v1/ecommerce")
public class EcommerceController {

    Date date = new Date();

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("verify")
    public ResponseEntity<?> ecommerceVerify(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);

        String account_number = jsonObject.getString("account_number");
        String description = jsonObject.getString("description");
        float amount = jsonObject.getFloat("amount");

        Map<String, String> response = new HashMap<>();

        Account objAcc = accountService.findAccountByNumber(account_number);
        Transaction objTrans = new Transaction();
        objTrans.setTrans_date(date);
        objTrans.setTrans_type("Thanh toán mua hàng trực tuyến");
        objTrans.setDescription(description);
        if(objAcc != null){
            objTrans.setStatus("00");
            transactionService.create(objTrans);

            response.put("errCode", "00");
            response.put("errDesc", "Thành công");
            response.put("description", description);
            response.put("amount", amount +"");
            response.put("url", "testBankingAPI/login");
        }else{
            objTrans.setStatus("05");
            transactionService.create(objTrans);

            response.put("errCode", "05");
            response.put("errDesc", "Số tài khoản không tồn tại");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
