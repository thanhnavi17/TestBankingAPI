package banking.api.service.transaction;

import banking.api.model.Transaction;
import banking.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public Transaction getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Transaction obj) {
        if(obj != null){
            transactionRepository.save(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Transaction obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
