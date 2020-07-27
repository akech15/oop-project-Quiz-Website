package ge.edu.freeuni.server.repository.friends;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.server.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class FriendsRepositoryImpl implements FriendsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public FriendshipStatusType getFriendshipStatus(UserEntity firstUser, UserEntity secondUser) {
        String query = String.format(
                "SELECT status FROM friends WHERE sender_id = \'%d\' AND receiver_id = \'%d\';",
                firstUser.getId(),
                secondUser.getId()
        );

        String status = jdbcTemplate.queryForObject(query, String.class);

        return status == null ? FriendshipStatusType.STRANGERS : Enum.valueOf(FriendshipStatusType.class, status);
    }

    @Override
    public boolean sendRequest(UserEntity sender, UserEntity receiver) {
        String query = String.format(
                "INSERT INTO friends (sender_id, receiver_id, status) " +
                "values (\'%d\', \'%d\', \'%s\');",
                sender.getId(),
                receiver.getId(),
                String.valueOf(FriendshipStatusType.PENDING)
        );
        try{
            jdbcTemplate.execute(query);
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
