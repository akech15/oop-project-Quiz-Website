package ge.edu.freeuni.server.repository;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    public QuizRepositoryImpl() {
    }

    @Override
    public QuizEntity getById(long id) {
        return null;
    }


}
