package ge.edu.freeuni.server.model.user;

import lombok.*;
import org.graalvm.compiler.lir.LIRInstruction;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    private long id;
    private String username;
    private String password;
    private static final long defaultId = -1;

    public UserEntity(String username, String password) {
        setUsername(username);
        setPassword(password);
        setId(UserEntity.defaultId);
    }
}
