package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;

public class QuizHelper {

	public static Quiz toDTO(QuizEntity from) {
		return Quiz.builder()
				.title(from.getTitle())
				.build();
	}

}
