package banking.api.service.transaction;

import banking.api.model.Transaction;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService extends Action<Transaction, Integer> {
}
