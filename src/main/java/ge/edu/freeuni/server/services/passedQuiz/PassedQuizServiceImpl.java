package ge.edu.freeuni.server.services.passedQuiz;

import ge.edu.freeuni.api.helper.PassedQuizHelper;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class PassedQuizServiceImpl implements PassedQuizService {

    private PassedQuiz currentPassedQuiz;

    @Autowired
    private PassedQuizRepositoryImpl passedQuizRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private QuizRepositoryImpl quizRepository;

    @Override
    public void startQuiz(PassedQuiz quiz) {
        currentPassedQuiz = quiz;
        passedQuizRepository.startQuiz(PassedQuizHelper.passedQuizToEntity(userRepository, quizRepository, quiz));
    }

    @Override
    public PassedQuiz finishQuiz() {
        long quizId = currentPassedQuiz.getId();

        currentPassedQuiz = null;
        return getPassedQuizById(quizId);
    }

    @Override
    public PassedQuiz getPassedQuizById(long id) {

        return PassedQuizHelper.entityToPassedQuiz(
                userRepository,
                quizRepository,
                passedQuizRepository.getPassedQuizById(id)
        );

    }

}
