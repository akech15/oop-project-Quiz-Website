package ge.edu.freeuni.server.repository;

import ge.edu.freeuni.server.model.quiz.QuizEntity;

public interface QuizRepository {

	QuizEntity getById(long id);

}
