package ge.edu.freeuni.server.repository.question;

import ge.edu.freeuni.server.model.question.QuestionEntity;

public interface QuestionRepository {

    boolean addQuestion(QuestionEntity questionEntity);

    QuestionEntity getQuestionById(long id);

}
