package ge.edu.freeuni.api.model.passedQuiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassedQuiz {

    private long id;
    private User user;
    private Quiz quiz;
    private long score;
    private Date startDate;
    private Date endDate;
    private String duration;

}
