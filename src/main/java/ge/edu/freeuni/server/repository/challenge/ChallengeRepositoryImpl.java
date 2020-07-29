package ge.edu.freeuni.server.repository.challenge;

import ge.edu.freeuni.server.model.challenge.ChallengeEntity;
import ge.edu.freeuni.server.model.mail.MailEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChallengeRepositoryImpl implements ChallengeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ChallengeEntity> challengeRawMapper = (ResultSet result, int numRow) ->
    {
        ChallengeEntity entity1 = new ChallengeEntity();
        entity1.setId(result.getLong("id"));
        entity1.setQuizId(result.getLong("quiz_id"));
        entity1.setSenderId(result.getLong("sender_id"));
        entity1.setReceiverId(result.getLong("receiver_id"));
        entity1.setSenderScore(result.getLong("sender_score"));
        return entity1;
    };

    @Override
    public ChallengeEntity getChallengeById(long id) {
        String query = String.format(
                "SELECT * FROM challenges WHERE id = \'%d\'",
                id
        );
        return jdbcTemplate.queryForObject(query, challengeRawMapper);
    }

    @Override
    public void addChallenge(ChallengeEntity challengeEntity) {
        String query = String.format(
                "insert into challenges (sender_id, receiver_id, quiz_id, sender_score) " +
                        "values (\'%d\', \'%d\', \'%d\', \'%d\');",
                challengeEntity.getSenderId(),
                challengeEntity.getReceiverId(),
                challengeEntity.getQuizId(),
                challengeEntity.getSenderScore()
        );
        jdbcTemplate.update(query);
    }

    @Override
    public void removeChallenge(ChallengeEntity challengeEntity) {
        String query = String.format("delete from challenges where id = \'%d\'", challengeEntity.getId());
        jdbcTemplate.update(query);
    }

    @Override
    public List<ChallengeEntity> searchChallengesByReceiver(UserEntity receiver) {
        String query = String.format(
                "SELECT id FROM challenges WHERE receiver_id = \'%d\'",
                receiver.getId()
        );

        List<Long> ids = new ArrayList<>();
        ids.addAll(jdbcTemplate.queryForList(query, Long.class));

        List<ChallengeEntity> challenges = new ArrayList<>();

        for (long id :
                ids) {
            challenges.add(this.getChallengeById(id));
        }

        return challenges;
    }

    @Override
    public List<ChallengeEntity> searchChallengesBySender(UserEntity sender) {
        String query = String.format(
                "SELECT id FROM challenges WHERE sender_id = \'%d\'",
                sender.getId()
        );

        List<Long> ids = new ArrayList<>();
        ids.addAll(jdbcTemplate.queryForList(query, Long.class));


        List<ChallengeEntity> challenges = new ArrayList<>();

        for (long id :
                ids) {
            challenges.add(this.getChallengeById(id));
        }

        return challenges;
    }
}
