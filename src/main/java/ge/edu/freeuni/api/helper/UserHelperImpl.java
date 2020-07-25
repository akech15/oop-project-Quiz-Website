package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.user.UserEntity;

public class UserHelperImpl {

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
