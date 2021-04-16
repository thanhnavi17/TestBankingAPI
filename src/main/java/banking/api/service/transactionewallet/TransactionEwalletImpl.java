package banking.api.service.transactionewallet;

import banking.api.model.TransactionEwallet;
import banking.api.repository.TransactionEwalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionEwalletImpl implements TransactionEwalletService{

    @Autowired
    private TransactionEwalletRepository transactionEwalletRepository;
    @Override
    public List<TransactionEwallet> getAll() {
        return null;
    }

    @Override
    public TransactionEwallet getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(TransactionEwallet obj) {
        transactionEwalletRepository.save(obj);
        return false;
    }

    @Override
    public boolean update(TransactionEwallet obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
