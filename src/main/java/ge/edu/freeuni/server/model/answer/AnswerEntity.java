package ge.edu.freeuni.server.model.answer;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerEntity {

    private long id;
    private long questionId;
    private String userAnswer;
    private long passedQuizId;

}
