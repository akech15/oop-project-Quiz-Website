package ge.edu.freeuni.server.services.answer;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.server.repository.answer.AnswerRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public boolean isAnswerCorrect(Answer answer) {
        return answerRepository.isAnswerCorrect(AnswerConverter.AnswerToEntity(answer, quizRepository));
    }

    @Override
    public boolean addAnswer(Answer answer) {
        return answerRepository.addAnswer(AnswerConverter.AnswerToEntity(answer, quizRepository));
    }

}
