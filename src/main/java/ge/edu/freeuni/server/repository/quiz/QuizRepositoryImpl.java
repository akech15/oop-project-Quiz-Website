package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<String> getAllQuizNames() {
        List<String> result = new ArrayList<>();
        String query = "select name from quiz";
        result.addAll(jdbcTemplate.queryForList(query, String.class));
        return result;
    }

    @Override
    public boolean addQuiz(Quiz quiz) {

        return true;
    }


}
