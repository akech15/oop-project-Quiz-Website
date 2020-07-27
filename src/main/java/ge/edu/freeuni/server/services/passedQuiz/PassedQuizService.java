package ge.edu.freeuni.server.services.passedQuiz;

import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;

public interface PassedQuizService {

    void startQuiz(PassedQuiz quiz);

    PassedQuiz finishQuiz();

    PassedQuiz getPassedQuizById(long id);

}
