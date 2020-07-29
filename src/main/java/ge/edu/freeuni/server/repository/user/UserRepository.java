package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.server.model.user.UserEntity;

public interface UserRepository {

    boolean addUserToDB(UserEntity userEntity);

    boolean isUserValid(UserEntity userEntity);

    UserEntity getUserById(long id);

}
