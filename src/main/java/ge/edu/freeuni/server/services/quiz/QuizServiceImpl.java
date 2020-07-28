package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
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
    private UserRepositoryImpl userRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    private void setActiveQuiz(Quiz quiz){
        activeQuiz = quiz;
    }

    @Override
    public boolean addQuiz(Quiz quiz) {
        setActiveQuiz(quiz);
        return quizRepository.addQuiz(QuizConverter.quizToEntity(quiz));
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

}
