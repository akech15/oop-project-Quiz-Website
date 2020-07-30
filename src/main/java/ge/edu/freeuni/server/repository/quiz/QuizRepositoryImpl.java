package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
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


    public QuizEntity getQuizById(long id) {
        String query = String.format("select * from quiz where id = \'%d\';", id);
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
    public List<QuizEntity> getAllQuizzes() {
        List<Long> result = new ArrayList<>();
        String query = "select id from quiz;";
        result.addAll(jdbcTemplate.queryForList(query, Long.class));
        List<QuizEntity> res = new ArrayList<>();
        for(long id : result){
            res.add(this.getQuizById(id));
        }

        return res;
    }

    @Override
    public boolean addQuiz(QuizEntity quizEntity) {
        Date date = quizEntity.getCreationDate();
        java.sql.Date dateDB = DateUtils.getDbDate(date);
        String query = String.format(
                "INSERT INTO quiz (name, creator_id, description, creation_date)" +
                        " values (\'%s\', \'%d\', \'%s\', \'%s\');",
                quizEntity.getName(),
                quizEntity.getCreatorId(),
                quizEntity.getDescription(),
                dateDB);

        try {
            jdbcTemplate.execute(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public QuizEntity getQuizByIdentifiers(QuizEntity quizEntity) {

        Date creationDate = quizEntity.getCreationDate();
        java.sql.Date creationDateDB = DateUtils.getDbDate(creationDate);

        String query = String.format(
                "select * from quiz where name = \'%s\' AND " +
                        "description = \'%s\' AND " +
                        "creator_id = \'%d\' AND " +
                        "creation_date = \'%s\';",
                quizEntity.getName(),
                quizEntity.getDescription(),
                quizEntity.getCreatorId(),
                creationDateDB
        );
        System.out.println("bechdavs? " + query);
        return jdbcTemplate.queryForObject(query, quizRawMapper);

    }


}
