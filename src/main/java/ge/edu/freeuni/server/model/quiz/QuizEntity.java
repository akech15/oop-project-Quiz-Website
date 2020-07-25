package ge.edu.freeuni.server.model.quiz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizEntity {
	private long id;
	private long creator_id;
	private String name;
	private String creation_date;
}
