package ge.edu.freeuni.api.model.mail;

import ge.edu.freeuni.api.model.user.User;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private long id;
    private User sender;
    private User receiver;
    private String context;

}

