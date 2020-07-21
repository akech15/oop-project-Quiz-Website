package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.api.model.user.User;

import java.sql.SQLException;

public interface UserRepository {
    boolean addUserToDB(User user) throws SQLException;
    boolean isValid(User user) throws SQLException;
}
