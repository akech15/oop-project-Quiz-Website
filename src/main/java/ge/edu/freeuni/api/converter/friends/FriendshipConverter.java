package ge.edu.freeuni.api.converter.friends;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.friends.Friendship;
import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.server.model.friends.FriendshipEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;

public final class FriendshipConverter {

    public static Friendship entityToFriends(UserRepository userRepository,
                                             FriendshipEntity friendsEntity) {
        return Friendship.builder()
                .sender(UserConverter.entityToUser(userRepository.getUserById(friendsEntity.getSenderId())))
                .receiver(UserConverter.entityToUser(userRepository.getUserById(friendsEntity.getReceiverId())))
                .status(Enum.valueOf(FriendshipStatusType.class, friendsEntity.getStatus()))
                .build();
    }

    public static FriendshipEntity friendsToEntity(UserRepository userRepository,
                                                   Friendship friends) {
        return FriendshipEntity.builder()
                .senderId(friends.getSender().getId())
                .receiverId(friends.getReceiver().getId())
                .status(String.valueOf(friends.getStatus()))
                .build();
    }

}
