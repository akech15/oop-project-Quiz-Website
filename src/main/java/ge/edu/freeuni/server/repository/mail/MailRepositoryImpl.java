package ge.edu.freeuni.server.repository.mail;

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
public class MailRepositoryImpl implements MailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<MailEntity> mailRawMapper = (ResultSet result, int numRow) ->
    {
        MailEntity entity1 = new MailEntity();
        entity1.setId(result.getLong("id"));
        entity1.setContext(result.getString("context"));
        entity1.setSenderId(result.getLong("sender_id"));
        entity1.setReceiverId(result.getLong("receiver_id"));
        return entity1;
    };


    @Override
    public MailEntity getMailById(long id) {
        String query = String.format(
                "SELECT * FROM mail WHERE id = \'%d\'",
                id
        );
        return jdbcTemplate.queryForObject(query, mailRawMapper);
    }

    @Override
    public void addMail(MailEntity mail) {
        String query = String.format(
                "INSERT INTO mail (sender_id, receiver_id, context) " +
                        "values(\'%d\', \'%d\', \'%s\')",
                mail.getSenderId(),
                mail.getReceiverId(),
                mail.getContext()
        );

        jdbcTemplate.update(query);

    }


    @Override
    public void removeMail(MailEntity mail) {
        String query = String.format("REMOVE FROM mail WHERE id = \'%d\'", mail.getId());
        jdbcTemplate.update(query);
    }

    @Override
    public List<MailEntity> searchMailsByReceiver(UserEntity receiver) {
        String query = String.format(
                "SELECT * FROM mail WHERE receiver_id = \'%d\'",
                receiver.getId()
        );

        List<Long> ids = jdbcTemplate.queryForList(query, Long.class);

        List<MailEntity> mails = new ArrayList<>();

        for (long id :
                ids) {
            mails.add(this.getMailById(id));
        }

        return mails;
    }

    @Override
    public List<MailEntity> searchMailsBySender(UserEntity sender) {
        String query = String.format(
                "SELECT * FROM mail WHERE sender_id = \'%d\'",
                sender.getId()
        );

        List<Long> ids = jdbcTemplate.queryForList(query, Long.class);

        List<MailEntity> mails = new ArrayList<>();

        for (long id :
                ids) {
            mails.add(this.getMailById(id));
        }

        return mails;
    }


}
