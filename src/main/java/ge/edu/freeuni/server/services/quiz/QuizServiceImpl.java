package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.helper.QuizHelperImpl;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizHelperImpl quizHelper;


    @Override
    public boolean addQuiz(Quiz quiz) {
        return quizRepository.addQuiz(quizHelper.quizToEntity(quiz));
    }

}
