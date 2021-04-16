package banking.api.service.user;

import banking.api.model.User;
import banking.api.service.Action;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends Action<User, Integer> {
    boolean loginCheck(String username, String password);
}
