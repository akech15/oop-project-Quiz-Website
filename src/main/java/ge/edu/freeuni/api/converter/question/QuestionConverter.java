package ge.edu.freeuni.api.converter.question;

import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.server.model.question.QuestionEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class QuestionConverter {

    public static Question entityToQuestion(UserRepository userRepository,
                                            QuizRepository quizRepository,
                                            QuestionEntity entity) {
        return Question.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .correctAnswerIndex(entity.getCorrectAnswerIndex())
                .quiz(QuizConverter.entityToQuiz(userRepository, quizRepository.getQuizById(entity.getQuizId())))
                .type(Enum.valueOf(QuestionType.class, entity.getType()))
                .choices(StringUtils.stringToList(entity.getChoices(), ','))
                .pictureURL(entity.getPictureURL())
                .build();
    }

    public static QuestionEntity questionToEntity(QuizRepository quizRepository,
                                                  Question question) {
        return QuestionEntity.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .correctAnswerIndex(question.getCorrectAnswerIndex())
                .quizId(question.getQuiz().getId())
                .type(String.valueOf(question.getType()))
                .correctAnswer(question.getCorrectAnswer())
                .choices(StringUtils.listToString(question.getChoices(), ','))
                .pictureURL(question.getPictureURL())
                .build();
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

    public static List<Question> entityToQuestionList(UserRepository userRepository,
                                                      QuizRepository quizRepository,
                                                      List<QuestionEntity> list) {

        List<Question> res = new ArrayList<>();

        for (QuestionEntity questionEntity :
                list) {
            res.add(QuestionConverter.entityToQuestion(userRepository, quizRepository, questionEntity));
        }

        return res;

    }

}
