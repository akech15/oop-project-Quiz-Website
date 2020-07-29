package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;

import java.text.ParseException;
import java.util.List;

public abstract class QuizService {

    public abstract Quiz getQuizByIdentifiers(Quiz quiz);

    public abstract boolean addQuiz(Quiz quiz);

    public abstract List<String> getAllQuizNames();

    public abstract Quiz getQuizById(long id);

    public abstract Quiz getActiveQuiz();

    public abstract void finishMakingQuiz();
}
