package ge.edu.freeuni.server.model.question;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionEntity {

    private long id;
    private long quizId;
    private long maxScore;

    private String category;
    private String question;
    private String type;
    private String answers;
}
