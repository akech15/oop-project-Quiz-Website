package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.model.user.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    User getUserById(long id);

    List<User> getUsersByUsernameFragment(String usernameFragment);

    List<User> getAllUsers();

    User getUserByUsernameAndPassword(String username, String password);
}
