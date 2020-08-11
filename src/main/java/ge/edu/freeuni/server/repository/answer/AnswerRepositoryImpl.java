package ge.edu.freeuni.server.repository.answer;

import ge.edu.freeuni.server.model.answer.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<AnswerEntity> answerRawMapper = (ResultSet result, int numRow) ->
    {
        AnswerEntity entity1 = new AnswerEntity();
        entity1.setId(result.getLong("id"));
        entity1.setPassedQuizId(result.getLong("passed_quiz_id"));
        entity1.setUserAnswer(result.getString("user_answer"));
        entity1.setQuestionId(result.getLong("question_id"));
        return entity1;
    };

    @Override
    public AnswerEntity getAnswerById(long id) {
        String query = String.format("SELECT * from answer WHERE id = \'%d\';", id);
        return jdbcTemplate.queryForObject(query, answerRawMapper);
    }

    @Override
    public boolean addAnswer(AnswerEntity answer) {
        String query = String.format("insert into answer (question_id, user_answer, passed_quiz_id) values (" +
                        "\'%d\', \'%s\', \'%d\');",
                answer.getQuestionId(),
                answer.getUserAnswer(),
                answer.getPassedQuizId()
        );

        try {
            jdbcTemplate.execute(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
