package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.utils.Wyvili;

import java.util.List;

public interface QuizService {

    Quiz getQuizByIdentifiers(Quiz quiz);

    boolean startMakingQuiz(Quiz quiz);

    List<String> getAllQuizNames();

    Quiz getQuizById(long id);

    Quiz getActiveQuiz();

    Quiz finishMakingQuiz();

    List<Quiz> getAllQuizzes();

    List<Wyvili<User, Long>> getTopRatedUsersByQuiz(Quiz quiz);

    List<Wyvili<Quiz, Long>> getTopRatedQuizzes();

	List<Quiz> getQuizesByUserId(long id);
}
