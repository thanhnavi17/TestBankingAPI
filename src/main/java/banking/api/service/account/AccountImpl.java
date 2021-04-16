package banking.api.service.account;

import banking.api.model.Account;
import banking.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountByNumber(String account_number) {
        Account obj = accountRepository.findAccountByNumber(account_number);
        return obj;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Account obj) {
        accountRepository.save(obj);
        return false;
    }

    @Override
    public boolean update(Account obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
