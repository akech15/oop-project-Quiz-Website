package ge.edu.freeuni.server.model.friends;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendshipEntity {

    private long senderId;
    private long receiverId;
    private String status;
}
