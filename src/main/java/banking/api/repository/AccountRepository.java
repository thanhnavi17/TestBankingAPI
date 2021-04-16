package banking.api.repository;

import banking.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("Select a from Account a where a.account_number = ?1")
    Account findAccountByNumber(String account_number);
}
