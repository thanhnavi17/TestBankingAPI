package banking.api.service.transactionewallet;

import banking.api.model.TransactionEwallet;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface TransactionEwalletService extends Action<TransactionEwallet, Integer> {
}
