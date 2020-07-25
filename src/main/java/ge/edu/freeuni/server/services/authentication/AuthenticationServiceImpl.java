package ge.edu.freeuni.server.services.authentication;

import ge.edu.freeuni.api.helper.UserHelperImpl;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepositoryImpl userRepository;

    private User activeUser;

    public User getActiveUser() {
        return activeUser;
    }

    private void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    private void cancelActiveUser(){
        this.activeUser = null;
    }

    @Autowired
    private UserHelperImpl userHelper;

    @Override
    public boolean logIn(User user) throws SQLException {
        boolean isValid = userRepository.isUserValid(userHelper.userToEntity(user));
        if(isValid) setActiveUser(user);
        return isValid;
    }

    @Override
    public void logOut(User user) {
        cancelActiveUser();
    }
}
