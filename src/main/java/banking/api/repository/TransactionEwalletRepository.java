package banking.api.repository;

import banking.api.model.TransactionEwallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEwalletRepository extends JpaRepository<TransactionEwallet, Integer> {
}
