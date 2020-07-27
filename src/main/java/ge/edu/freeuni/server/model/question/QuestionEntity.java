package ge.edu.freeuni.server.model.question;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {

    private long id;
    private long quizId;
    private long correctAnswerIndex;

    private String category;
    private String question;
    private String type;
    private String answers;
    private String pictureURL;

}
