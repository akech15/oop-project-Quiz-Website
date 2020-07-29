package ge.edu.freeuni.server.services.friendship;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.friends.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Override
    public FriendshipStatusType getFriendshipStatus(User firstUser, User secondUser) {
        return friendsRepository
                .getFriendshipStatus(UserConverter.userToEntity(firstUser),
                        UserConverter.userToEntity(secondUser));
    }

    @Override
    public boolean sendRequest(User sender, User receiver) {
        return friendsRepository
                .sendRequest(UserConverter.userToEntity(sender),
                        UserConverter.userToEntity(receiver));
    }

    @Override
    public boolean approveRequest(User sender, User receiver) {
        return friendsRepository
                .approveRequest(UserConverter.userToEntity(sender),
                        UserConverter.userToEntity(receiver));
    }

    @Override
    public boolean removeRequest(User sender, User receiver) {
        return friendsRepository
                .removeRequest(UserConverter.userToEntity(sender),
                        UserConverter.userToEntity(receiver));
    }

    @Override
    public boolean removeFriend(User sender, User receiver) {
        return friendsRepository
                .removeFriend(UserConverter.userToEntity(sender),
                        UserConverter.userToEntity(receiver));
    }

    @Override
    public List<User> getAllFriendRequest(User user) {
        return UserConverter.entityToFriendshipList(friendsRepository.getAllFriendRequest(user.getId()));
    }
}
