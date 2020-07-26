package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<QuizEntity> quizRawMapper = (ResultSet result, int numRow) ->
    {
        QuizEntity entity1 = new QuizEntity();
        entity1.setId(result.getLong("id"));
        entity1.setCreationDate(result.getDate("creation_date"));
        entity1.setDescription(result.getString("description"));
        entity1.setName(result.getString("name"));
        entity1.setCreatorId(result.getLong("creator_id"));
        return entity1;
    };

    @Override
    public QuizEntity getQuizByName(String name) {
        String query = String.format("select * from quiz where name = \'%s\';", name);
        return jdbcTemplate.queryForObject(query, quizRawMapper);
    }

    @Override
    public List<String> getAllQuizNames() {
        List<String> result = new ArrayList<>();
        String query = "select q.name from quiz q";
        result.addAll(jdbcTemplate.queryForList(query, String.class));
        return result;
    }

    @Override
    public boolean addQuiz(QuizEntity quizEntity) {
        Date date = quizEntity.getCreationDate();
        java.sql.Date dateDB = getDbDate(date);
        String query = String.format(
                "INSERT INTO quiz (name, creator_id, description, creation_date )" +
                        " values (\'%s\', \'%s\', \'%s\', \'%s\');",
                quizEntity.getName(),
                quizEntity.getCreatorId(),
                quizEntity.getDescription(),
                dateDB);
        System.out.println(query);
        jdbcTemplate.execute(query);
        return true;
    }

    @Override
    public int getCreatorId(String quizName) {
        String query = "select creator_id from quiz where name=" + quizName;
        return jdbcTemplate.queryForObject(query, Integer.class);
    }


    private java.sql.Date getDbDate(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // your template here
            String d = formatter.format(date);
            java.util.Date dateStr = formatter.parse(d);
            return new java.sql.Date(dateStr.getTime());
        } catch (Exception ex) {
            throw new IllegalStateException();
        }
    }
}
