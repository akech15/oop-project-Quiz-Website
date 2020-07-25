package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public QuizRepositoryImpl() {
    }

    @Override
    public QuizEntity getQuizById(long id) {
        String query = "select * from quiz where id = " + id;
        return null;
    }

    @Override
    public List<String> getAllQuizNames() {
        List<String> result = new ArrayList<>();
        String query = "select name from quiz";
        result.addAll(jdbcTemplate.queryForList(query, String.class));
        return result;
    }

    @Override
    public boolean addQuiz(QuizEntity quizEntity) {
        String querry = "INSERT INTO quiz (id, name, creator_id, description)" +
                " values (" +
                quizEntity.getName() + ", " +
                quizEntity.getCreatorId() + ", " +
                quizEntity.getDescription() + ");";
        jdbcTemplate.execute(querry);
        return true;
    }
}
