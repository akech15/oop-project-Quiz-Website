package ge.edu.freeuni.api.model.answer;

import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.quiz.Quiz;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {

    private long id;
    private Question question;
    private String userAnswer;
    private PassedQuiz passedQuiz;

}
