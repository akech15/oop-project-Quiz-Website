package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.utils.Wyvili;

import java.util.List;

public interface QuizRepository {

    QuizEntity getQuizById(long id);

    List<String> getAllQuizNames();

    boolean addQuiz(QuizEntity quizEntity);

    QuizEntity getQuizByIdentifiers(QuizEntity quizToEntity);

    List<QuizEntity> getAllQuizzes();

    List<Wyvili<UserEntity, Long>> getTopRatedUsersByQuizId(long quiz_id);

    List<Wyvili<QuizEntity, Long>> getTopRatedQuizzes();

    List<QuizEntity> getAllQuizesByUserId(long id);
}
