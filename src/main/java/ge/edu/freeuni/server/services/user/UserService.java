package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.model.user.User;

import java.sql.SQLException;

public interface UserService {

    /**
     *
     * @return if added to the database returns true
     *         false otherwise
     */
    boolean addUser(User user) ;

    /**
     *
     * @param user
     * @param friend
     */
    void addFriend(User user, User friend);

    void removeFriend(User user, User friend);

    void acceptRequest(User user, User friend);
}
