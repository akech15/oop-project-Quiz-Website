package ge.edu.freeuni.server.services.question;

import ge.edu.freeuni.api.model.question.Question;

public interface QuestionService {

    boolean addQuestion(Question question);

    Question getQuestionById(long id);


}
