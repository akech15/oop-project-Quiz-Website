package ge.edu.freeuni.server.services.authentication;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public boolean logIn(User user) throws SQLException {

        return userRepository.isValid(user);
    }

    @Override
    public void logOut(User user) {

    }
}
