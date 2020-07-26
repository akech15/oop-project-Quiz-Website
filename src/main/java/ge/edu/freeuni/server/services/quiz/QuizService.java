package ge.edu.freeuni.server.services.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;

import java.text.ParseException;

public interface QuizService {

    boolean addQuiz(Quiz quiz) throws ParseException;

}
