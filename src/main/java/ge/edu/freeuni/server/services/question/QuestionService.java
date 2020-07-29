package ge.edu.freeuni.server.services.question;

import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.quiz.Quiz;

import java.util.List;

public interface QuestionService {

    boolean addQuestion(Question question);

    Question getQuestionById(long id);

    List<Question> getAllQuestionsByQuiz(Quiz quiz);

}
