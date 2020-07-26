package ge.edu.freeuni.server.model.quiz;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizEntity {
	private long id;
	private long creatorId;
	private String name;
	private Date creationDate;
	private String description;

	public QuizEntity(long creatorId, String name, String description) {
		setCreatorId(creatorId);
		setName(name);
		setDescription(description);
		setCreationDate(null);
		setId(-1);
	}

}
