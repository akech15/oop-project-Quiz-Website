package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.model.user.User;

import java.sql.SQLException;

public interface UserService {

    boolean addUser(User user) ;

    User getUserById(long id);

}
