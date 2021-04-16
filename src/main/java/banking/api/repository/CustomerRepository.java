package banking.api.repository;

import banking.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.id_card = ?1")
    Customer findByIdCard(String id_card);

    @Query("SELECT c FROM Customer c WHERE c.cif = ?1")
    Customer findByCif(String cif);
}
