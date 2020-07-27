package ge.edu.freeuni.api.model.answer;

import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.quiz.Quiz;

public class Answer {

    private long id;
    private Question question;
    private String userAnswer;
    private Quiz passedQuiz;

}
