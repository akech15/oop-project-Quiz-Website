package ge.edu.freeuni.server.services.question;

import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.server.repository.question.QuestionRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepositoryImpl questionRepository;

    @Autowired
    private QuizRepositoryImpl quizRepository;

    @Override
    public boolean addQuestion(Question question) {
        return questionRepository.addQuestion(QuestionConverter.questionToEntity(quizRepository, question));
    }

}
