package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.server.model.user.UserEntity;

public interface UserService {

    /**
     *
     * @return if added to the database returns true
     *         false otherwise
     */
    boolean addUser(UserEntity user);

}
