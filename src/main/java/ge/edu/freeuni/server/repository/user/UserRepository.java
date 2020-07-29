package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.server.model.user.UserEntity;

import java.util.List;

public interface UserRepository {

    boolean addUserToDB(UserEntity userEntity);

    boolean isUserValid(UserEntity userEntity);

    UserEntity getUserById(long id);

    List<UserEntity> getUsersByUsernameFragment(String usernameFragment);

    UserEntity getUsersByUsername(String username);
}
