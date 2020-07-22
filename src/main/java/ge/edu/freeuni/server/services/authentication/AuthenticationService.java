package ge.edu.freeuni.server.services.authentication;


import ge.edu.freeuni.api.model.user.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


public interface AuthenticationService {
    /**
     *
     * @return true if log-in was successful
     *         false otherwise
     */
    boolean logIn(User user) throws SQLException;

    void logOut(User user);
}
