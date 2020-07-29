package ge.edu.freeuni.server.repository.question;

import ge.edu.freeuni.server.model.question.QuestionEntity;

import java.util.List;

public interface QuestionRepository {

    boolean addQuestion(QuestionEntity questionEntity);

    QuestionEntity getQuestionById(long id);

    List<QuestionEntity> getAllQuestionsByQuizId(long id);
}
