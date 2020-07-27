package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;

import java.text.ParseException;
import java.util.List;

public interface QuizService {

    boolean addQuiz(Quiz quiz) throws ParseException;

    List<String> getAllQuizNames();

    User getCreator(String quizName);

    Quiz getQuiz(String quizName);

    Quiz getActiveQuiz();

    void QuitQuiz();
}
