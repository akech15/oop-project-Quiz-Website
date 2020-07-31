package ge.edu.freeuni.server.repository.answer;

import ge.edu.freeuni.server.model.answer.AnswerEntity;

public interface AnswerRepository {

    AnswerEntity getAnswerById(long id);

    boolean addAnswer(AnswerEntity answer);

//    boolean isAnswerCorrect(AnswerEntity answer);

}
