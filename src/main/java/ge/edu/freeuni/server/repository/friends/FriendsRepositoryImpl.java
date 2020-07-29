package ge.edu.freeuni.server.repository.friends;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendsRepositoryImpl implements FriendsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepositoryImpl userRepository;

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
        try {
            jdbcTemplate.execute(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean approveRequest(UserEntity sender, UserEntity receiver) {
        String query = String.format(
                "UPDATE friends SET status = \'%s\' WHERE sender_id = \'%d\' AND receiver_id = \'%d\';",
                String.valueOf(FriendshipStatusType.APPROVED),
                sender.getId(),
                receiver.getId()
        );

        try {
            jdbcTemplate.update(query);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean removeRequest(UserEntity sender, UserEntity receiver) {
        String query = String.format(
                "DELETE FROM friends WHERE sender_id = \'%d\' AND receiver_id = \'%d\';",
                sender.getId(),
                receiver.getId()
        );

        try {
            jdbcTemplate.update(query);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean removeFriend(UserEntity sender, UserEntity receiver) {
        String query = String.format(
                "DELETE FROM friends WHERE (sender_id = \'%d\' AND receiver_id = \'%d\') OR " +
                        "(sender_id = \'%d\' AND receiver_id = \'%d\');",
                sender.getId(),
                receiver.getId(),
                receiver.getId(),
                sender.getId()
        );

        try {
            jdbcTemplate.update(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserEntity> getAllFriendRequest(long userId) {
        List<UserEntity> result = new ArrayList<>();
        String query = String.format(
                "select sender_id from friends where receiver_id = \'%d\' and status = \'%s\'",
                userId,
                FriendshipStatusType.PENDING
        );
        List<Long> friendId = new ArrayList<>();
        friendId.addAll(jdbcTemplate.queryForList(query, Long.class));
        for (Long id : friendId) {
            result.add(userRepository.getUserById(id));
        }
        return result;
    }

}
