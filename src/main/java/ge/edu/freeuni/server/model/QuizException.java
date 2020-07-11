package ge.edu.freeuni.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizException extends Exception {

	private String code;

	public QuizException(String code) {
		super(code);
		this.code = code;
	}

}
