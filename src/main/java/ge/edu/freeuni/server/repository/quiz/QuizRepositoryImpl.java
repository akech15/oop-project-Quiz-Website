package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.utils.Wyvili;
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
    private UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<QuizEntity> quizRawMapper = (ResultSet result, int numRow) ->
    {
        QuizEntity entity1 = new QuizEntity();
        entity1.setId(result.getLong("id"));
        entity1.setCreationDate(new Date(result.getLong("creation_date")));
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
        for (long id : result) {
            res.add(this.getQuizById(id));
        }

        return res;
    }

    @Override
    public boolean addQuiz(QuizEntity quizEntity) {
        long dateDB = quizEntity.getCreationDate().getTime();
        String query = String.format(
                "INSERT INTO quiz (name, creator_id, description, creation_date)" +
                        " values (\'%s\', \'%d\', \'%s\', \'%d\');",
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
        Long creationDateDB = quizEntity.getCreationDate().getTime();
        String query = String.format(
                "select * from quiz where name = \'%s\' AND " +
                        "description = \'%s\' AND " +
                        "creator_id = \'%d\' AND " +
                        "creation_date = \'%d\';",
                quizEntity.getName(),
                quizEntity.getDescription(),
                quizEntity.getCreatorId(),
                creationDateDB
        );
        return jdbcTemplate.queryForObject(query, quizRawMapper);
    }

    @Override
    public List<Wyvili<UserEntity, Long>> getTopRatedUsersByQuizId(long quiz_id) {

        String queryIds = String.format(
                "SELECT user_id FROM passed_quiz WHERE quiz_id = \'%d\' ORDER BY score desc",
                quiz_id
        );

        String queryScores = String.format(
                "SELECT score FROM passed_quiz WHERE quiz_id = \'%d\' ORDER BY 1 desc",
                quiz_id
        );
        List<Long> ids = jdbcTemplate.queryForList(queryIds, Long.class);
        List<Long> counts = jdbcTemplate.queryForList(queryScores, Long.class);
        List<Wyvili<UserEntity, Long>> res = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++){
            res.add(new Wyvili<>(userRepository.getUserById(ids.get(i)), counts.get(i)));
        }

        return res;
    }

    @Override
    public List<Wyvili<QuizEntity, Long>> getTopRatedQuizzes() {
        String queryIds = "select quiz_id from passed_quiz group by quiz_id order by count(quiz_id) desc;";
        String queryCounts = "select count(quiz_id) from passed_quiz group by quiz_id order by 1 desc;";
        List<Long> ids = jdbcTemplate.queryForList(queryIds, Long.class);
        List<Long> counts = jdbcTemplate.queryForList(queryCounts, Long.class);
        List<Wyvili<QuizEntity, Long>> res = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++){
            res.add(new Wyvili<>(this.getQuizById(ids.get(i)), counts.get(i)));
        }

        return res;
    }

    @Override
    public List<QuizEntity> getAllQuizesByUserId(long user_id) {
        String query = String.format(
                "SELECT id FROM quiz WHERE creator_id = \'%d\';",
                user_id
        );
        List<Long> ids = new ArrayList<>(jdbcTemplate.queryForList(query, Long.class));
        List<QuizEntity> res = new ArrayList<>();
        for (long id : ids) {
            res.add(this.getQuizById(id));
        }
        return res;
    }
}
