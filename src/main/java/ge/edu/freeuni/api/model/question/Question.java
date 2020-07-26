package ge.edu.freeuni.api.model.question;

import java.util.List;

public class Question {

    private long quizId;
    private String question;
    private QuestionType type;
    private long maxScore;
    private List<String> answers;
    private QuestionCategoryType category;

    public static void main(String[] args) {
        QuestionType sad = QuestionType.valueOf("A");
        System.out.println(sad.name());
    }







}
