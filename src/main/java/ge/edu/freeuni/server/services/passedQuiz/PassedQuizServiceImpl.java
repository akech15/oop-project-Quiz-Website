package ge.edu.freeuni.server.services.passedQuiz;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.converter.passedQuiz.PassedQuizConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepository;
import ge.edu.freeuni.server.repository.question.QuestionRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassedQuizServiceImpl implements PassedQuizService {

    private PassedQuiz currentPassedQuiz;

    @Autowired
    private PassedQuizRepository passedQuizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerService answerService;


    @Override
    public void startQuiz(PassedQuiz quiz) {
        currentPassedQuiz = quiz;
        passedQuizRepository.startQuiz(PassedQuizConverter.passedQuizToEntity(quizRepository, quiz));
    }

    @Override
    public PassedQuiz finishQuiz() {
        long quizId = currentPassedQuiz.getId();
        passedQuizRepository.finishQuiz(PassedQuizConverter.passedQuizToEntity(quizRepository, currentPassedQuiz));
        currentPassedQuiz = null;
        return getPassedQuizById(quizId);
    }


    @Override
    public PassedQuiz getPassedQuizById(long id) {

        return PassedQuizConverter.entityToPassedQuiz(
                userRepository,
                quizRepository,
                passedQuizRepository.getPassedQuizById(id)
        );
    }

    @Override
    public long getPassedQuizScore(PassedQuiz passedQuiz) {

        List<AnswerEntity> answersByPassedQuiz = passedQuizRepository.getAnswersByPassedQuiz(PassedQuizConverter.
                passedQuizToEntity(quizRepository, passedQuiz));
        List<Answer> passedQuizAnswers = AnswerConverter.entityToAnswerList(
                questionRepository,
                quizRepository,
                passedQuizRepository,
                userRepository,
                answersByPassedQuiz);

        long score = 0;

        for (Answer answer :
                passedQuizAnswers) {
            if (answerService.isAnswerCorrect(answer)) {
                score++;
            }
        }

        return score;
    }

}
