package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<UserEntity> userRawMapper = (ResultSet result, int numRow) ->
    {
        UserEntity entity1 = new UserEntity();
        entity1.setId(result.getLong("id"));
        entity1.setUsername(result.getString("username"));
        entity1.setPassword(result.getString("password"));
        return entity1;
    };

    @Override
    public boolean addUserToDB(UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        if (isUsernameUsed(username))
            return false;
        jdbcTemplate.execute("insert into user (username, password) values ("
                + "\"" + username + "\" , \"" + password + "\");");
        return true;
    }

    private boolean isUsernameUsed(String username) {
        String search = "select u.username from user u where u.username = \"" + username + "\";";
        List<String> users = new ArrayList<>();
        users.addAll(jdbcTemplate.queryForList(search, String.class));
        return users.size() != 0;
    }

    @Override
    public boolean isUserValid(UserEntity userEntity) {
        List<String> users = new ArrayList<>();
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        String search = "select u.username from user u where u.username = \"" + username + "\""
                + " and u.password = \"" + password + "\";";
        users.addAll(jdbcTemplate.queryForList(search, String.class));
        return users.size() == 1;
    }


    @Override
    public UserEntity getUserById(long id) {
        String query = String.format("select * from user where id = %d", id);
        return jdbcTemplate.queryForObject(query, userRawMapper);
    }
}
