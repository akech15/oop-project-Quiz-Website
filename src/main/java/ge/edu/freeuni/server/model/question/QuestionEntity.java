package ge.edu.freeuni.server.model.question;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionEntity {

    private long id;
    private long quizId;
    private long correctAnswerIndex;
    private String question;
    private String type;
    private String answers;
    private String pictureURL;
    private String correctAnswer;
}
