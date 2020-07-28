package ge.edu.freeuni.server.services.passedQuiz;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.converter.passedQuiz.PassedQuizConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.server.repository.answer.AnswerRepositoryImpl;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepositoryImpl;
import ge.edu.freeuni.server.repository.question.QuestionRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import ge.edu.freeuni.server.services.answer.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassedQuizServiceImpl implements PassedQuizService {

    private PassedQuiz currentPassedQuiz;

    @Autowired
    private PassedQuizRepositoryImpl passedQuizRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private QuizRepositoryImpl quizRepository;

    @Autowired
    private AnswerRepositoryImpl answerRepository;

    @Autowired
    private QuestionRepositoryImpl questionRepository;

    @Autowired
    private AnswerServiceImpl answerService;


    @Override
    public void startQuiz(PassedQuiz quiz) {
        currentPassedQuiz = quiz;
        passedQuizRepository.startQuiz(PassedQuizConverter.passedQuizToEntity(userRepository, quizRepository, quiz));
    }

    @Override
    public PassedQuiz finishQuiz() {
        long quizId = currentPassedQuiz.getId();
        passedQuizRepository.finishQuiz(PassedQuizConverter.passedQuizToEntity(userRepository, quizRepository, currentPassedQuiz));
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

        List<Answer> passedQuizAnswers =
                AnswerConverter.entityToAnswerList(questionRepository, quizRepository,
                        passedQuizRepository, userRepository,
                        passedQuizRepository.
                                            getAnswersByPassedQuiz(PassedQuizConverter.
                                            passedQuizToEntity(userRepository, quizRepository, passedQuiz)));

        long score = 0;

        for (Answer answer:
        passedQuizAnswers) {
            if(answerService.isAnswerCorrect(answer)){
                score++;
            }
        }

        return score;
    }

}
