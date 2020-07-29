package ge.edu.freeuni.server.services.authentication;


import ge.edu.freeuni.api.model.user.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


public interface AuthenticationService {

    public User getActiveUser();

    boolean logIn(User user);

    void logOut(User user);
}
