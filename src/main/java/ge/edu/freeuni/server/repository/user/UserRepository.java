package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.api.model.user.User;

public interface UserRepository {
    boolean addUserToDB(User user);

    boolean isUserValid(User user);
}
