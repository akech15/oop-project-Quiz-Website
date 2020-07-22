package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public boolean addUser(User user) throws SQLException {
        return userRepository.addUserToDB(user);
    }

    @Override
    public void addFriend(User user, User friend) {

    }

    @Override
    public void removeFriend(User user, User friend) {

    }

    @Override
    public void acceptRequest(User user, User friend) {

    }
}
