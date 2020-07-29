package ge.edu.freeuni.server.services.answer;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.server.repository.answer.AnswerRepository;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepository;
import ge.edu.freeuni.server.repository.question.QuestionRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PassedQuizService passedQuizService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PassedQuizRepository passedQuizRepository;

    @Override
    public boolean isAnswerCorrect(Answer answer) {
        return answerRepository.isAnswerCorrect(AnswerConverter.AnswerToEntity(answer, quizRepository));
    }

    @Override
    public boolean addAnswer(Answer answer) {
        answer.setPassedQuiz(passedQuizService.getActivePassedQuiz());
        return answerRepository.addAnswer(AnswerConverter.AnswerToEntity(answer, quizRepository));
    }

    @Override
    public Answer getAnswerById(int id) {
        return AnswerConverter.AnswerEntityToAnswer(answerRepository
                        .getAnswerById(id),
                questionRepository,
                quizRepository,
                passedQuizRepository,
                userRepository
        );
    }

}
