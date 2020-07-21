package ge.edu.freeuni.server.repository.quiz;

import ge.edu.freeuni.server.model.quiz.QuizEntity;

public interface QuizRepository {

	QuizEntity getById(long id);

}
