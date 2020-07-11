package ge.edu.freeuni.server.repository;

import ge.edu.freeuni.server.model.quiz.QuizEntity;
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
