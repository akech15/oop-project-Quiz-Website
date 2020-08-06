package ge.edu.freeuni.server.repository.friends;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.server.model.user.UserEntity;

import java.util.List;

public interface FriendsRepository {

    FriendshipStatusType getFriendshipStatus(UserEntity firstUser, UserEntity secondUser);

    boolean sendRequest(UserEntity sender, UserEntity receiver);

    boolean approveRequest(long sender, long receiver);

    boolean removeRequest(UserEntity sender, UserEntity receiver);

    boolean removeFriend(UserEntity sender, UserEntity receiver);

    List<UserEntity> getAllFriendRequest(long userId);


}
