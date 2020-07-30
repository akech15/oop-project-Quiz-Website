package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import jdk.internal.net.http.common.Pair;

import java.util.List;

public interface QuizService {

    Quiz getQuizByIdentifiers(Quiz quiz);

    boolean startMakingQuiz(Quiz quiz);

    List<String> getAllQuizNames();

    Quiz getQuizById(long id);

    Quiz getActiveQuiz();

    void finishMakingQuiz();

    List<Quiz> getAllQuizzes();

    List<Pair<User, Long>> getTopRatedUsersByQuiz(Quiz quiz);

    List<Pair<Quiz, Long>> getTopRatedQuizzes();

}
