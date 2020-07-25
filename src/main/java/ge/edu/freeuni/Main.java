package ge.edu.freeuni;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.services.quiz.QuizServiceImpl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        QuizRepositoryImpl quizService = new QuizRepositoryImpl();
        Quiz quiz = new Quiz();
        quiz.setName("a");
        quiz.setCreator_id(15);
        quizService.addQuiz(quiz);

    }
}
