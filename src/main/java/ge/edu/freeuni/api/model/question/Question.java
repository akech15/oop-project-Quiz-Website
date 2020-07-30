package ge.edu.freeuni.api.model.question;

import ge.edu.freeuni.api.model.quiz.Quiz;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private long id;
    private Quiz quiz;
    private String question;
    private QuestionType type;
    private long correctAnswerIndex;
    private List<String> answers;
    private QuestionCategoryType category;
    private String pictureURL;
    private String correctAnswer;


    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
