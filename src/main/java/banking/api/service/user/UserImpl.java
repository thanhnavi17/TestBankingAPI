package banking.api.service.user;

import banking.api.model.User;
import banking.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean loginCheck(String username, String password) {
        User obj = userRepository.findByUsername(username);
        if(obj != null && obj.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public boolean create(User obj) {
        return false;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
