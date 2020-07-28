package ge.edu.freeuni.server.services.user;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositoryImpl userRepository;


    @Override
    public boolean addUser(User user) {
        return userRepository.addUserToDB(UserConverter.userToEntity(user));
    }

    @Override
    public User getUserById(long id) {
        return UserConverter.entityToUser(userRepository.getUserById(id));
    }
}
