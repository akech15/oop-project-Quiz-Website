package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.utils.Wyvili;

import java.util.List;

public interface UserRepository {

    boolean addUserToDB(UserEntity userEntity);

    boolean isUserValid(UserEntity userEntity);

    UserEntity getUserById(long id);

    List<UserEntity> getUsersByUsernameFragment(String usernameFragment);

    UserEntity getUsersByUsername(String username);

    List<UserEntity> getAllUsers();

    List<Wyvili<UserEntity, Long>> getTopRatedUsers();
}
