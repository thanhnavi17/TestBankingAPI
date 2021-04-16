package banking.api.controller;

import banking.api.model.Transaction;
import banking.api.model.User;
import banking.api.repository.UserRepository;
import banking.api.service.transaction.TransactionService;
import banking.api.service.user.UserService;
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
public class UserController {

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody String requestBody){
        JSONObject jsonObject = new JSONObject(requestBody);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        Transaction objTrans = new Transaction();
        objTrans.setTrans_date(date);
        objTrans.setTrans_type("Kiểm tra đăng nhập");

        Map<String, String> response = new HashMap<>();

        if(userService.loginCheck(username, password)){
            objTrans.setStatus("00");
            objTrans.setDescription("Đăng nhập thành công");
            transactionService.create(objTrans);

            response.put("status","00");
            User user = userRepository.findByUsername(username);
            response.put("cif", user.getCif());
            response.put("account_number", jsonObject.getString("account_number"));

        }else{
            objTrans.setStatus("04");
            objTrans.setDescription("Thông tin đăng nhập không đúng");
            transactionService.create(objTrans);

            response.put("status","04");
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
