package ge.edu.freeuni.server.repository.passedQuiz;

import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;
import ge.edu.freeuni.server.repository.answer.AnswerRepositoryImpl;
import ge.edu.freeuni.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PassedQuizRepositoryImpl implements PassedQuizRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AnswerRepositoryImpl answerRepository;

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

        try {
            jdbcTemplate.update(query);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean finishQuiz(PassedQuizEntity passedQuizEntity) {
        Date endDate = passedQuizEntity.getEndDate();
        java.sql.Date endDateDB = DateUtils.getDbDate(endDate);
        long score = passedQuizEntity.getScore();
        Date duration = passedQuizEntity.getDuration();
        java.sql.Date durationDB = DateUtils.getDbDate(duration);

        String query = String.format(
                "UPDATE passed_quiz SET score = \'%s\', end_date = \'%s\', duration = \'%s\'" +
                        "WHERE id = \'%d\';",
                score,
                endDateDB,
                durationDB,
                passedQuizEntity.getId()
        );

        jdbcTemplate.update(query);
        return true;
    }

    @Override
    public List<AnswerEntity> getAnswersByPassedQuiz(PassedQuizEntity passedQuizEntity) {

        String query = String.format(
                "SELECT id FROM answer WHERE passed_quiz_id = \'%d\'",
                passedQuizEntity.getId()
        );

        List<Long> answerIds = jdbcTemplate.queryForList(query, Long.class);

        List<AnswerEntity> answers = new ArrayList<>();

        for (long answerId:
             answerIds) {
            answers.add(answerRepository.getAnswerById(answerId));
        }

        return answers;
    }

}
