package ge.edu.freeuni.api.model.quiz;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {

    private long creator_id;
    private String name;

}
