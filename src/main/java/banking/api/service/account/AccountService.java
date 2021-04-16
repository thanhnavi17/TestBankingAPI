package banking.api.service.account;

import banking.api.model.Account;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends Action<Account, Integer> {
    Account findAccountByNumber(String account_number);
}
