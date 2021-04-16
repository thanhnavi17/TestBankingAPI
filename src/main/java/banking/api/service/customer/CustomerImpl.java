package banking.api.service.customer;

import banking.api.model.Customer;
import banking.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findByIdCard(String id_card) {
        Customer obj = customerRepository.findByIdCard(id_card);
        return obj;
    }

    @Override
    public Customer findByCif(String cif) {
        Customer obj = customerRepository.findByCif(cif);
        return obj;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Customer obj) {
        return false;
    }

    @Override
    public boolean update(Customer obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
