package ge.edu.freeuni.server.repository.passedQuiz;


import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;

import java.util.List;

public interface PassedQuizRepository {

    PassedQuizEntity getPassedQuizById(long id);

    boolean startQuiz(PassedQuizEntity passedQuizEntity);

    boolean finishQuiz(PassedQuizEntity passedQuizEntity);

    List<AnswerEntity> getAnswersByPassedQuiz(PassedQuizEntity passedQuizEntity);

    PassedQuizEntity getPassedQuizByIdentifiers(PassedQuizEntity passedQuizEntity);
}
