package ge.edu.freeuni.api.model.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private long id;
    private String name;
    private String username;
    private String password;
}
