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
    private int quizIdCounter;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public QuizRepositoryImpl() {
    }

    @Override
    public QuizEntity getById(long id) {
        String query = "select * from quiz where id = " + id;
        return null;
    }

    @Override
    public List<String> getAllQuiz() {
        List<String> result = new ArrayList<>();
        String query = "select name from quiz";
        result.addAll(jdbcTemplate.queryForList(query, String.class));
        return result;
    }

    @Override
    public boolean addQuiz(Quiz quiz) {
        String query = "insert into quiz values(?, ?, ?)";
        return jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws DataAccessException, SQLException {
                preparedStatement.setInt(1, quizIdCounter);
                preparedStatement.setString(2, quiz.getName());
                preparedStatement.setLong(3, quiz.getCreator_id());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                preparedStatement.setDate(4, Date.valueOf(dtf.format(now)));
                return null;
            }
        });
    }


}
