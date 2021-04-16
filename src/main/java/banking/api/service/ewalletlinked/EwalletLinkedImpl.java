package banking.api.service.ewalletlinked;

import banking.api.model.EwalletLinked;
import banking.api.repository.EwalletLinkedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EwalletLinkedImpl implements EwalletLinkedService{

    @Autowired
    private EwalletLinkedRepository ewalletLinkedRepository;

    @Override
    public List<EwalletLinked> getAll() {
        return null;
    }

    @Override
    public EwalletLinked getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(EwalletLinked obj) {
        if(obj != null){
            ewalletLinkedRepository.save(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(EwalletLinked obj) {
        ewalletLinkedRepository.save(obj);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
