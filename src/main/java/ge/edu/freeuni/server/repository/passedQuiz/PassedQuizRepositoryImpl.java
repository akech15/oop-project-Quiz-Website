package ge.edu.freeuni.server.repository.passedQuiz;

import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;
import ge.edu.freeuni.server.repository.answer.AnswerRepositoryImpl;
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
        Date startDate = new Date(result.getLong("start_date"));
        entity1.setStartDate(startDate);
        Date endDate = new Date(result.getLong("end_date"));
        entity1.setEndDate(endDate);
        Date duration = new Date(result.getLong("duration"));
        entity1.setDuration(duration);
        return entity1;
    };

    @Override
    public PassedQuizEntity getPassedQuizById(long id) {
        String query = String.format("SELECT * from passed_quiz WHERE id = \'%d\';", id);
        return jdbcTemplate.queryForObject(query, passedQuizRawMapper);
    }

    @Override
    public boolean startQuiz(PassedQuizEntity passedQuizEntity) {

        long startDateDB = passedQuizEntity.getStartDate().getTime();

        long endDateDB = passedQuizEntity.getEndDate().getTime();

        long durationDB = passedQuizEntity.getDuration().getTime();

        String query = String.format(
                "INSERT INTO passed_quiz (user_id, quiz_id, score, start_date, end_date, duration)" +
                        "values (\'%d\', \'%d\', \'%d\', \'%d\', \'%d\', \'%d\');",
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
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean finishQuiz(PassedQuizEntity passedQuizEntity) {

        long endDateDB = passedQuizEntity.getEndDate().getTime();
        long score = passedQuizEntity.getScore();
        long durationDB = passedQuizEntity.getDuration().getTime();

        String query = String.format(
                "UPDATE passed_quiz SET score = \'%d\', end_date = \'%d\', duration = \'%d\'" +
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

        for (long answerId :
                answerIds) {
            answers.add(answerRepository.getAnswerById(answerId));
        }

        return answers;
    }

    @Override
    public PassedQuizEntity getPassedQuizByIdentifiers(PassedQuizEntity passedQuizEntity) {

        long startDateDB = passedQuizEntity.getStartDate().getTime();

        String query = String.format(
                "SELECT * FROM passed_quiz WHERE user_id = \'%d\' AND quiz_id = \'%d\' AND start_date = \'%d\';",
                passedQuizEntity.getUserId(),
                passedQuizEntity.getQuizId(),
                startDateDB
        );

        return jdbcTemplate.queryForObject(query, passedQuizRawMapper);

    }

}
