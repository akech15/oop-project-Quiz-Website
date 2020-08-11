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
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private AuthenticationService authenticationService;


    @Override
    public PassedQuiz getActivePassedQuiz() {
        return currentPassedQuiz;
    }

    @Override
    public boolean startQuiz(PassedQuiz passedQuiz) {

        passedQuiz.setUser(authenticationService.getActiveUser());
        passedQuiz.setStartDate(new Date());
        passedQuiz.setEndDate(new Date());
        passedQuiz.setDuration("0:0:0");

        boolean startQuiz = passedQuizRepository
                .startQuiz(PassedQuizConverter
                        .passedQuizToEntity(quizRepository,
                                passedQuiz
                        )
                );

        currentPassedQuiz = this.getPassedQuizByIdentifiers(passedQuiz);

        return startQuiz;
    }

    @Override
    public PassedQuiz finishQuiz() {

        currentPassedQuiz.setEndDate(new Date());
        currentPassedQuiz.setScore(this.getPassedQuizScore(currentPassedQuiz));

        long duration = currentPassedQuiz.getEndDate().getTime() - currentPassedQuiz.getStartDate().getTime();

        currentPassedQuiz.setDuration(DateUtils.millisecondsToTimeFormat(duration));

        passedQuizRepository.finishQuiz(PassedQuizConverter.passedQuizToEntity(quizRepository, currentPassedQuiz));
        long quizId = currentPassedQuiz.getId();
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

        List<AnswerEntity> answersByPassedQuiz = passedQuizRepository.getAnswersByPassedQuiz(PassedQuizConverter
                                                    .passedQuizToEntity(quizRepository, passedQuiz));

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

    @Override
    public PassedQuiz getPassedQuizByIdentifiers(PassedQuiz passedQuiz) {
        return PassedQuizConverter
                .entityToPassedQuiz(userRepository,
                        quizRepository,
                        passedQuizRepository
                                .getPassedQuizByIdentifiers(
                                        PassedQuizConverter
                                                .passedQuizToEntity(quizRepository,
                                                        passedQuiz
                                                )
                                )
                );
    }

    @Override
    public List<PassedQuiz> getPassedQuizzesByUserId(long userId) {

        return PassedQuizConverter.entityToPassedQuizList(userRepository,
                                                            quizRepository,
                passedQuizRepository.getPassedQuizzesByUserId(userId));

    }

}
