package ge.edu.freeuni.api.model.quiz;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {

    private String name;
    private String description;
    private Date creationDate;

}
