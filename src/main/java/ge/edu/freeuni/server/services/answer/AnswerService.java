package ge.edu.freeuni.server.services.answer;

import ge.edu.freeuni.api.model.answer.Answer;

public interface AnswerService {

    boolean isAnswerCorrect(Answer answer);

    boolean addAnswer(Answer answer);

    Answer getAnswerById(int id);
}
