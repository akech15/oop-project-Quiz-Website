package ge.edu.freeuni.server.repository.passedQuiz;


import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;

public interface PassedQuizRepository {

    PassedQuizEntity getPassedQuizById(long id);

    boolean startQuiz(PassedQuizEntity passedQuizEntity);

    boolean finishQuiz(PassedQuizEntity passedQuizEntity);

}
