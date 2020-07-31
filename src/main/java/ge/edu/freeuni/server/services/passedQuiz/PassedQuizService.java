package ge.edu.freeuni.server.services.passedQuiz;

import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;

import java.util.List;

public interface PassedQuizService {

    PassedQuiz getActivePassedQuiz();

    boolean startQuiz(PassedQuiz quiz);

    PassedQuiz finishQuiz();

    PassedQuiz getPassedQuizById(long id);

    long getPassedQuizScore(PassedQuiz passedQuiz);

    PassedQuiz getPassedQuizByIdentifiers(PassedQuiz passedQuiz);

    List<PassedQuiz> getPassedQuizzesByUserId(long userId);

}
