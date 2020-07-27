package ge.edu.freeuni.server.model.friends;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipEntity {

    private long senderId;
    private long receiverId;
    private String status;
}
