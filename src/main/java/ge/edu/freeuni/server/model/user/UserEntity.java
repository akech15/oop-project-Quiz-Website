package ge.edu.freeuni.server.model.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    private long id;
    private String name;
    private String username;
    private String password;
    private static final long defaultId = -1;

    public UserEntity(String username, String password) {
        setUsername(username);
        setPassword(password);
        setId(UserEntity.defaultId);
    }
}
