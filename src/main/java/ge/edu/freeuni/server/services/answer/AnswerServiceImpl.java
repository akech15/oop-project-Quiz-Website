package ge.edu.freeuni.server.services.answer;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.server.repository.answer.AnswerRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepositoryImpl answerRepository;

    @Autowired
    private QuizRepositoryImpl quizRepository;

    @Override
    public boolean isAnswerCorrect(Answer answer) {
        return answerRepository.isAnswerCorrect(AnswerConverter.AnswerToEntity(answer, quizRepository));
    }

}
