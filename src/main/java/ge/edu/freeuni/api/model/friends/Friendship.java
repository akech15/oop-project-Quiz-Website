package ge.edu.freeuni.api.model.friends;

import ge.edu.freeuni.api.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Builder
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {

    private User sender;
    private User receiver;
    private FriendshipStatusType status;

}
