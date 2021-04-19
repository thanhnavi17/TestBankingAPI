package banking.api.service.transactionecom;

import banking.api.model.TransactionEcom;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface TransactionEcomService extends Action<TransactionEcom, Integer> {
}
