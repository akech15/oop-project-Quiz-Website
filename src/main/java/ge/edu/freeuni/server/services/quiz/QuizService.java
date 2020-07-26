package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;

import java.text.ParseException;
import java.util.List;

public interface QuizService {

    boolean addQuiz(Quiz quiz) throws ParseException;

    List<String> getAllQuizNames();

    int getCreator(String quizName);
}
