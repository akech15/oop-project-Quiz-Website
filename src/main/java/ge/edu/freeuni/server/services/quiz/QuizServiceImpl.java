package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.helper.QuizHelper;
import ge.edu.freeuni.api.helper.UserHelperImpl;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private Quiz activeQuiz = null;

    @Autowired
    private QuizRepositoryImpl quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    private void setActiveQuiz(Quiz quiz){
        activeQuiz = quiz;
    }

    @Override
    public boolean addQuiz(Quiz quiz) {
        setActiveQuiz(quiz);
        return quizRepository.addQuiz(QuizHelper.quizToEntity(authenticationService,
                userRepository,
                quiz));
    }

    @Override
    public List<String> getAllQuizNames() {
        return quizRepository.getAllQuizNames();
    }

    @Override
    public User getCreator(String quizName) {
        return UserHelperImpl.entityToUser(userRepository.getUserById(quizRepository.getQuizByName(quizName).getCreatorId()));
    }

    @Override
    public Quiz getQuiz(String quizName) {
        return QuizHelper.entityToQuiz(quizRepository.getQuizByName(quizName));
    }

    @Override
    public Quiz getActiveQuiz() {
        return activeQuiz;
    }

    @Override
    public void QuitQuiz() {
        activeQuiz = null;
    }

}
