package banking.api.service;

import banking.api.model.Account;
import banking.api.model.Customer;
import banking.api.model.Transaction;
import banking.api.repository.CustomerRepository;
import banking.api.service.account.AccountService;
import banking.api.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CoreBankService {
    Date date = new Date();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    public Customer customerInfo(String cif){
        Customer objCustomer = customerRepository.findByCif(cif);
        return objCustomer;
    }

    public Map<String, String> transferMoney(String transfer_acc, String receive_acc, float amount,
                                             String description){
        Account objTransfer = accountService.findAccountByNumber(transfer_acc);
        Account objReceive = accountService.findAccountByNumber(receive_acc);
        Map<String, String> response = new HashMap<>();

        if(objTransfer != null){
            if(objReceive != null){
                if(objTransfer.getBalance() > amount){
                    objTransfer.setBalance(objTransfer.getBalance() - amount);
                    objReceive.setBalance(objReceive.getBalance() + amount);

                    accountService.update(objTransfer);
                    accountService.update(objReceive);

                    response.put("errCode", "00");
                    response.put("errDesc", "Thành công");

                    //Lưu transaction thực hiện chuyển khoản
                    Transaction objTrans = new Transaction();
                    objTrans.setTrans_type("Chuyển khoản");
                    objTrans.setTrans_date(date);
                    objTrans.setAmount(amount);
                    objTrans.setDescription(description);
                    transactionService.create(objTrans);
                    response.put("trans_id",objTrans.getTrans_id() +  "");
                }else{
                    response.put("errCode", "12");
                    response.put("errDesc", "Tài khoản không đủ để thực hiện chuyển khoản");
                }
            }else{
                response.put("errCode", "13");
                response.put("errDesc", "Tài khoản nhận không tồn tại");
            }
        }else{
            response.put("errCode", "14");
            response.put("errDesc", "Tài khoản chuyển không tồn tại");
        }

        return response;
    }
}
