package ge.edu.freeuni.api.model.challenge;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Challenge {

    private long id;
    private User sender;
    private User receiver;
    private Quiz quiz;
    private long senderScore;

}
