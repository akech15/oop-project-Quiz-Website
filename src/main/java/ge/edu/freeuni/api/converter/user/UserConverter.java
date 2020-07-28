package ge.edu.freeuni.api.converter.user;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static User entityToUser(UserEntity userEntity) {
        return User.builder().
                username(userEntity.getUsername()).
                password(userEntity.getPassword()).build();
    }

    public static UserEntity userToEntity(User user) {
        return UserEntity.builder().
                username(user.getUsername()).
                password(user.getPassword()).build();
    }

}
