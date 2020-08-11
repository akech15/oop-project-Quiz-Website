package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.utils.Wyvili;
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
        entity1.setName(result.getString("name"));
        return entity1;
    };

    @Override
    public boolean addUserToDB(UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        String name = userEntity.getName();
        if (isUsernameUsed(username))
            return false;
        String query = String.format(
                "insert into user (username, password, name) " +
                        "values (\'%s\', \'%s\', \'%s\');",
                username,
                password,
                name
        );
        jdbcTemplate.update(query);
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
        if (jdbcTemplate.queryForObject(query, userRawMapper) != null) {
            return jdbcTemplate.queryForObject(query, userRawMapper);
        }
        return null;
    }

    @Override
    public List<UserEntity> getUsersByUsernameFragment(String usernameFragment) {

        String query = "SELECT id FROM user WHERE username LIKE '%" + usernameFragment + "%';";
        List<Long> ids = jdbcTemplate.queryForList(query, Long.class);
        List<UserEntity> usersList = new ArrayList<>();
        for (long id : ids) {
            usersList.add(this.getUserById(id));
        }
        return usersList;
    }

    @Override
    public UserEntity getUsersByUsername(String username) {
        String query = "SELECT id FROM user WHERE username = '" + username + "';";
        List<Long> ids = jdbcTemplate.queryForList(query, Long.class);
        if (ids.size() == 0)
            return null;
        return this.getUserById(ids.get(0));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        String query = "SELECT id FROM user;";
        List<Long> ids = jdbcTemplate.queryForList(query, Long.class);
        List<UserEntity> usersList = new ArrayList<>();
        for (long id : ids) {
            usersList.add(this.getUserById(id));
        }
        return usersList;
    }

    @Override
    public List<Wyvili<UserEntity, Long>> getTopRatedUsers() {
        String queryIds = "select creator_id from quiz group by creator_id order by count(creator_id) desc;";
        String queryCounts = "select count(creator_id) from quiz group by creator_id order by 1 desc;";
        List<Long> ids = jdbcTemplate.queryForList(queryIds, Long.class);
        List<Long> counts = jdbcTemplate.queryForList(queryCounts, Long.class);
        List<Wyvili<UserEntity, Long>> res = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            res.add(new Wyvili<>(this.getUserById(ids.get(i)), counts.get(i)));
        }
        return res;
    }

}
