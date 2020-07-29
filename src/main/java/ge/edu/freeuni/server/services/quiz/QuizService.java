package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;

import java.util.List;

public interface QuizService {

    Quiz getQuizByIdentifiers(Quiz quiz);

    boolean startMakingQuiz(Quiz quiz);

    List<String> getAllQuizNames();

    Quiz getQuizById(long id);

    Quiz getActiveQuiz();

    void finishMakingQuiz();
}
