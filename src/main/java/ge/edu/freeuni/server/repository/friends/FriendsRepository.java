package ge.edu.freeuni.server.repository.friends;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.server.model.user.UserEntity;

public interface FriendsRepository {

    FriendshipStatusType getFriendshipStatus(UserEntity firstUser, UserEntity secondUser);

    boolean sendRequest(UserEntity sender, UserEntity receiver);

    boolean approveRequest(UserEntity sender, UserEntity receiver);

    boolean removeRequest(UserEntity sender, UserEntity receiver);

    boolean removeFriend(UserEntity sender, UserEntity receiver);

}
