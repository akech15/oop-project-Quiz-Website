package ge.edu.freeuni.server.model.challenge;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChallengeEntity {

    private long id;
    private long senderId;
    private long receiverId;
    private long quizId;
    private long senderScore;

}
