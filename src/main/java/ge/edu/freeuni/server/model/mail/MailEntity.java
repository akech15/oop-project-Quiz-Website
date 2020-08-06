package ge.edu.freeuni.server.model.mail;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailEntity {

    private long id;
    private long senderId;
    private long receiverId;
    private String context;
    private Date sent_date;

}
