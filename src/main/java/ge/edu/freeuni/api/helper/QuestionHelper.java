package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionCategoryType;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.server.model.question.QuestionEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.utils.StringUtils;

public final class QuestionHelper {

    public static Question entityToQuestion(QuizRepositoryImpl quizRepository,
                                            QuestionEntity entity) {
        return Question.builder().
                question(entity.getQuestion()).
                correctAnswerIndex(entity.getCorrectAnswerIndex()).
                quiz(QuizHelper.entityToQuiz(quizRepository.getQuizById(entity.getQuizId()))).
                type(Enum.valueOf(QuestionType.class, entity.getType())).
                category(Enum.valueOf(QuestionCategoryType.class, entity.getCategory())).
                answers(StringUtils.StringToList(entity.getAnswers(), ',')).
                pictureURL(entity.getPictureURL()).build();
    }

    public static QuestionEntity questionToEntity(QuizRepositoryImpl quizRepository,
                                                  Question question) {
        return QuestionEntity.builder().
                question(question.getQuestion()).
                correctAnswerIndex(question.getCorrectAnswerIndex()).
                quizId(quizRepository.getQuizByName(question.getQuiz().getName()).getId()).
                type(String.valueOf(question.getType())).
                category(String.valueOf(question.getCategory())).
                answers(StringUtils.listToString(question.getAnswers(), ',')).
                pictureURL(question.getPictureURL()).build();
    }

    public static String getJspFromType(String type) {
        QuestionType type1 = Enum.valueOf(QuestionType.class, type);
        switch (type1) {
            case QUESTION_RESPONSE:
                return "questionResponse";
            case FILL_IN_THE_BLANK:
                return "fillInTheBlank";
            case MULTIPLE_CHOICE:
                return "multipleChoice";
            default:
                return "pictureResponse";
        }
    }

}
