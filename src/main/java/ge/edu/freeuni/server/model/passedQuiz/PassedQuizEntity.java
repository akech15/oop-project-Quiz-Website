package ge.edu.freeuni.server.model.passedQuiz;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PassedQuizEntity {

    private long id;
    private long userId;
    private long quizId;
    private long score;
}
