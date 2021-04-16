package banking.api.repository;

import banking.api.model.EwalletLinked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EwalletLinkedRepository extends JpaRepository<EwalletLinked, Integer> {
    @Query("Select el from EwalletLinked el where el.account_number = ?1 and el.ewallet_id = ?2")
    EwalletLinked findByAccAndEwallet(String account_number, String ewallet_id);
}
