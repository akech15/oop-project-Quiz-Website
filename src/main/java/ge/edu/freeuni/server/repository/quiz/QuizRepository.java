package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;

import java.text.ParseException;
import java.util.List;

public interface QuizRepository {

    QuizEntity getQuizById(long id);

    List<String> getAllQuizNames();

    boolean addQuiz(QuizEntity quizEntity);


    void clearDBase();
}
