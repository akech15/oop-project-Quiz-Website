package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.user.UserEntity;

public interface UserRepository {

    boolean addUserToDB(UserEntity userEntity);

    boolean isUserValid(UserEntity userEntity);

    long getIdByUsername(String username);

}
