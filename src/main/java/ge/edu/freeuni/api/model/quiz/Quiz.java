package ge.edu.freeuni.api.model.quiz;

import ge.edu.freeuni.api.model.user.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {

    private long id;
    private User creator;
    private String name;
    private String description;
    private Date creationDate;

}
