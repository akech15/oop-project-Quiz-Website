package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private Quiz activeQuiz = null;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    private void setActiveQuiz(Quiz quiz) {
        activeQuiz = quiz;
    }

    @Override
    public Quiz getQuizByIdentifiers(Quiz quiz) {
        return QuizConverter.entityToQuiz(userRepository, quizRepository.getQuizByIdentifiers(QuizConverter.quizToEntity(quiz)));
    }

    @Override
    public boolean startMakingQuiz(Quiz quiz) {

        quiz.setCreationDate(new Date());
        User activeUser = authenticationService.getActiveUser();
        quiz.setCreator(authenticationService.getActiveUser());

        boolean addQuiz = quizRepository.addQuiz(QuizConverter.quizToEntity(quiz));

        setActiveQuiz(this.getQuizByIdentifiers(quiz));
        return addQuiz;
    }

    @Override
    public List<String> getAllQuizNames() {
        return quizRepository.getAllQuizNames();
    }

    @Override
    public Quiz getQuizById(long id) {
        return QuizConverter.entityToQuiz(userRepository, quizRepository.getQuizById(id));
    }

    @Override
    public Quiz getActiveQuiz() {
        return activeQuiz;
    }

    @Override
    public void finishMakingQuiz() {
        activeQuiz = null;
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return QuizConverter.entityToQuizList(userRepository, quizRepository.getAllQuizzes());
    }

//    @Override
//    public List<Pair<User, Long>> getTopRatedUsersByQuiz(Quiz quiz) {
//        return UserConverter.entityToUserPairList(quizRepository.getTopRatedUsersByQuizId(quiz.getId()));
//    }

//    @Override
//    public List<Pair<Quiz, Long>> getTopRatedQuizzes() {
//        return QuizConverter.entityToQuizPairList(userRepository, quizRepository.getTopRatedQuizzes());
//    }

}
