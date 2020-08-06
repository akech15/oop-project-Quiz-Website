package ge.edu.freeuni.api.model.mail;

import ge.edu.freeuni.api.model.user.User;
import lombok.*;

import java.util.Date;

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
    private Date sent_date;

}

