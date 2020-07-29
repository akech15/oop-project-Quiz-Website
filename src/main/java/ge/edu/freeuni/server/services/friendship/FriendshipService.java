package ge.edu.freeuni.server.services.friendship;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.api.model.user.User;

import java.util.List;

public interface FriendshipService {

    FriendshipStatusType getFriendshipStatus(User firstUser, User secondUser);

    boolean sendRequest(User sender, User receiver);

    boolean approveRequest(User sender, User receiver);

    boolean removeRequest(User sender, User receiver);

    boolean removeFriend(User sender, User receiver);

    List<User> getAllFriendRequest(User user);

}
