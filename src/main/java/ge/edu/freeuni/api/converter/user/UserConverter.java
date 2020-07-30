package ge.edu.freeuni.api.converter.user;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.user.UserEntity;
import jdk.internal.net.http.common.Pair;
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
                .build();
    }

    public static UserEntity userToEntity(User user) {

        return UserEntity.builder()
                .username(user.getUsername())
                .id(user.getId())
                .password(user.getPassword())
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

	public static List<Pair<User,Long>> entityToUserPairList(List<Pair<UserEntity, Long>> topRatedUsersByQuizId) {
	    List<Pair<User, Long> > res = new ArrayList<>();

        for (Pair<UserEntity, Long> pair:
             topRatedUsersByQuizId) {
            res.add(new Pair<>(UserConverter.entityToUser(pair.first), pair.second));
        }

        return res;
    }
}
