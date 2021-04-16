package banking.api.service.customer;

import banking.api.model.Customer;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService extends Action<Customer, Integer> {
    Customer findByIdCard(String id_card);
    Customer findByCif(String cif);
}
