package ge.edu.freeuni.server.model.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEntity {

    private long id;
    private String username;
    private String password;

}
