package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import org.springframework.stereotype.Repository;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    public QuizRepositoryImpl() {
    }

    @Override
    public QuizEntity getById(long id) {
        return null;
    }


}
