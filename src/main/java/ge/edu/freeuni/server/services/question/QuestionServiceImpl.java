package ge.edu.freeuni.server.services.question;

import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.server.repository.question.QuestionRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addQuestion(Question question) {
        return questionRepository.addQuestion(QuestionConverter.questionToEntity(quizRepository, question));
    }

    @Override
    public Question getQuestionById(long id) {
        return QuestionConverter.entityToQuestion(
                                                    userRepository,
                                                    quizRepository,
                                                    questionRepository.getQuestionById(id)
        );
    }

}
