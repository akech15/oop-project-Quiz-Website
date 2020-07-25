package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizRepositoryImpl quizRepository;

    public QuizServiceImpl() {
    }

    @Override
    public Boolean addQuiz(Quiz quiz) {
        return true;
    }
}
