package ge.edu.freeuni.api.converter.user;

import ge.edu.freeuni.api.converter.friends.FriendshipConverter;
import ge.edu.freeuni.api.model.friends.Friendship;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.friends.FriendshipEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public static User entityToUser(UserEntity userEntity) {
        return User.builder().
                username(userEntity.getUsername()).
                password(userEntity.getPassword()).
                id(userEntity.getId()).build();
    }

    public static UserEntity userToEntity(User user) {
        return UserEntity.builder().
                username(user.getUsername()).
                id(user.getId()).
                password(user.getPassword()).build();
    }

    public static List<User> entityToFriendshipList(List<UserEntity> list){

        List<User> res = new ArrayList<>();

        for (UserEntity userEntity:
                list) {
            res.add(UserConverter.entityToUser(userEntity));
        }

        return res;

    }

}
