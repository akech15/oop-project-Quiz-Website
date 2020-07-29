package ge.edu.freeuni.server.services.authentication;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepositoryImpl userRepository;

    private User activeUser;

    @Override
    public boolean isUserValid(User user) {
        return userRepository.isUserValid(UserConverter.userToEntity(user));
    }

    public User getActiveUser() {
        return activeUser;
    }

    private void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    private void cancelActiveUser() {
        this.activeUser = null;
    }

    @Override
    public boolean logIn(User user) {
        boolean isValid = isUserValid(user);
        if (isValid) setActiveUser(user);
        return isValid;
    }

    @Override
    public void logOut(User user) {
        cancelActiveUser();
    }
}
