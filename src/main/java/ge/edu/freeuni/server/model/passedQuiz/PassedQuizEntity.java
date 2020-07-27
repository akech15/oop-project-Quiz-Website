package ge.edu.freeuni.server.model.passedQuiz;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassedQuizEntity {

    private long id;
    private long userId;
    private long quizId;
    private long score;
    private Date startDate;
    private Date endDate;
    private Date duration;

}
