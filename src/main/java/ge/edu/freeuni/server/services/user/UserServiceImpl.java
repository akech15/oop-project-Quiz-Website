package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        return userRepository.addUserToDB(UserConverter.userToEntity(user));
    }

    @Override
    public User getUserById(long id) {
        return UserConverter.entityToUser(userRepository.getUserById(id));
    }

    @Override
    public List<User> getUsersByUsernameFragment(String usernameFragment) {
        return UserConverter.entityToFriendshipList(userRepository.getUsersByUsernameFragment(usernameFragment));
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        UserEntity userEntity = userRepository.getUsersByUsername(username);
        if (userEntity != null && userEntity.getPassword().equals(password)) {
            return UserConverter.entityToUser(userEntity);
        }
        return null;
    }
}
