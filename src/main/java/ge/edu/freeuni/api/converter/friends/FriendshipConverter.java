package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.api.model.friends.Friendship;
import ge.edu.freeuni.server.model.friends.FriendshipEntity;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

public final class FriendsHelper {

    public static Friendship entityToFriends(UserRepositoryImpl userRepository,
                                             FriendshipEntity friendsEntity){
        return Friendship.builder().
                sender(UserHelperImpl.entityToUser(userRepository.getUserById(friendsEntity.getSenderId()))).
                receiver(UserHelperImpl.entityToUser(userRepository.getUserById(friendsEntity.getReceiverId()))).
                status(Enum.valueOf(FriendshipStatusType.class, friendsEntity.getStatus())).build();
    }

    public static FriendshipEntity friendsToEntity(UserRepositoryImpl userRepository,
                                                   Friendship friends){
        return FriendshipEntity.builder().
                senderId(userRepository.getIdByUsername(friends.getSender().getUsername())).
                receiverId(userRepository.getIdByUsername(friends.getReceiver().getUsername())).
                status(String.valueOf(friends.getStatus())).build();
    }

}
