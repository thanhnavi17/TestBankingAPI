package banking.api.repository;

import banking.api.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
    @Query("Select o from OTP o where o.cif = ?1")
    OTP findByCif(String cif);
}
