package ge.edu.freeuni.server.model.mail;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailEntity {

    private long id;
    private long senderId;
    private long receiverId;
    private String context;

}
