package banking.api.repository;

import banking.api.model.TransactionEcom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEcomRepository extends JpaRepository<TransactionEcom, Integer> {
}
