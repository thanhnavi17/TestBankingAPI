package banking.api.service.transactionecom;

import banking.api.model.Transaction;
import banking.api.model.TransactionEcom;
import banking.api.repository.TransactionEcomRepository;
import banking.api.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionEcomImpl implements TransactionEcomService {

    @Autowired
    private TransactionEcomRepository transactionEcomRepository;

    @Override
    public List<TransactionEcom> getAll() {
        return null;
    }

    @Override
    public TransactionEcom getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(TransactionEcom obj) {
        transactionEcomRepository.save(obj);
        return false;
    }

    @Override
    public boolean update(TransactionEcom obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
