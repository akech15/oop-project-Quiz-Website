package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import jdk.internal.net.http.common.Pair;

import java.util.List;

public interface QuizRepository {

    QuizEntity getQuizById(long id);

    List<String> getAllQuizNames();

    boolean addQuiz(QuizEntity quizEntity);

    QuizEntity getQuizByIdentifiers(QuizEntity quizToEntity);

    public List<QuizEntity> getAllQuizzes();

    List<Pair<UserEntity, Long>> getTopRatedUsersByQuizId(long quiz_id);

    List<Pair<QuizEntity, Long>> getTopRatedQuizzes();
}
