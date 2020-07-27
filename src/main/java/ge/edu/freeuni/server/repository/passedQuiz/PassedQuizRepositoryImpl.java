package ge.edu.freeuni.server.repository.passedQuiz;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;
import ge.edu.freeuni.server.model.question.QuestionEntity;
import ge.edu.freeuni.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Date;

@Repository
public class PassedQuizRepositoryImpl implements PassedQuizRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<PassedQuizEntity> passedQuizRawMapper = (ResultSet result, int numRow) ->
    {
        PassedQuizEntity entity1 = new PassedQuizEntity();
        entity1.setId(result.getLong("id"));
        entity1.setQuizId(result.getLong("quiz_id"));
        entity1.setUserId(result.getLong("user_id"));
        entity1.setScore(result.getLong("score"));
        entity1.setStartDate(result.getDate("start_date"));
        entity1.setEndDate(result.getDate("end_date"));
        entity1.setDuration(result.getDate("duration"));
        return entity1;
    };

    @Override
    public PassedQuizEntity getPassedQuizById(long id) {
        String query = String.format("SELECT * from passed_quiz WHERE id = \'%d\';", id);
        return jdbcTemplate.queryForObject(query, passedQuizRawMapper);
    }

    @Override
    public boolean startQuiz(PassedQuizEntity passedQuizEntity) {

        Date startDate = passedQuizEntity.getStartDate();
        java.sql.Date startDateDB = DateUtils.getDbDate(startDate);

        Date endDate = passedQuizEntity.getStartDate();
        java.sql.Date endDateDB = DateUtils.getDbDate(endDate);

        Date duration = passedQuizEntity.getStartDate();
        java.sql.Date durationDB = DateUtils.getDbDate(duration);


        String query = String.format(
                "INSERT INTO passed_quiz (id, user_id, quiz_id, score, start_date, end_date, duration)" +
                        "values (\'%d\', \'%d\', \'%d\', \'%d\', \'%s\', \'%s\', \'%s\');",
                passedQuizEntity.getId(),
                passedQuizEntity.getUserId(),
                passedQuizEntity.getQuizId(),
                passedQuizEntity.getScore(),
                startDateDB,
                endDateDB,
                durationDB
        );

        try{
            jdbcTemplate.update(query);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean finishQuiz(PassedQuizEntity passedQuizEntity) {
        // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        String query  = String.format(
//                "UPDATE passed_quiz SET score = \'%s\' WHERE sender_id = \'%d\' AND receiver_id = \'%d\';",
//                String.valueOf(FriendshipStatusType.APPROVED),
//                receiver.getId(),
//                sender.getId()
//        );
        return false;
    }

}
