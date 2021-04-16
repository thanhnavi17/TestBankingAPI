package banking.api.repository;

import banking.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u where u.username = ?1")
    public User findByUsername(String username);
}
