package ge.edu.freeuni.api.converter.user;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.utils.Wyvili;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public static User entityToUser(UserEntity userEntity) {
        if(userEntity == null)
            return null;
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }

    public static UserEntity userToEntity(User user) {

        return UserEntity.builder()
                .username(user.getUsername())
                .id(user.getId())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }

    public static List<User> entityToFriendshipList(List<UserEntity> list) {

        List<User> res = new ArrayList<>();

        for (UserEntity userEntity :
                list) {
            res.add(UserConverter.entityToUser(userEntity));
        }

        return res;

    }

	public static List<Wyvili<User,Long>> entityToUserPairList(List<Wyvili<UserEntity, Long>> topRatedUsersByQuizId) {
	    List<Wyvili<User, Long> > res = new ArrayList<>();

        for (Wyvili<UserEntity, Long> pair:
             topRatedUsersByQuizId) {
            res.add(new Wyvili<>(UserConverter.entityToUser(pair.first), pair.second));
        }

        return res;
    }
}
