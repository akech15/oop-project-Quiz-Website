package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.model.user.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUserById(long id);

    List<User> getUsersByUsernameFragment(String usernameFragment);

    User getUserByUsername(String username);
}
