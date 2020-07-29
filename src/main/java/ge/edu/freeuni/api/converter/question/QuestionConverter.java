package ge.edu.freeuni.api.converter.question;

import ge.edu.freeuni.api.converter.mail.MailConverter;
import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionCategoryType;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.server.model.mail.MailEntity;
import ge.edu.freeuni.server.model.question.QuestionEntity;
import ge.edu.freeuni.server.repository.question.QuestionRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import ge.edu.freeuni.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class QuestionConverter {

    public static Question entityToQuestion(UserRepository userRepository,
                                            QuizRepository quizRepository,
                                            QuestionEntity entity) {
        return Question.builder().
                id(entity.getId()).
                question(entity.getQuestion()).
                correctAnswerIndex(entity.getCorrectAnswerIndex()).
                quiz(QuizConverter.entityToQuiz(userRepository, quizRepository.getQuizById(entity.getQuizId()))).
                type(Enum.valueOf(QuestionType.class, entity.getType())).
                category(Enum.valueOf(QuestionCategoryType.class, entity.getCategory())).
                answers(StringUtils.stringToList(entity.getAnswers(), ',')).
                pictureURL(entity.getPictureURL()).build();
    }

    public static QuestionEntity questionToEntity(QuizRepository quizRepository,
                                                  Question question) {
        return QuestionEntity.builder().
                id(question.getId()).
                question(question.getQuestion()).
                correctAnswerIndex(question.getCorrectAnswerIndex()).
                quizId(question.getQuiz().getId()).
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

    public static List<Question> entityToQuestionList(UserRepository userRepository,
                                              QuizRepository quizRepository,
                                              List<QuestionEntity> list){

        List<Question> res = new ArrayList<>();

        for (QuestionEntity questionEntity:
                list) {
            res.add(QuestionConverter.entityToQuestion(userRepository, quizRepository, questionEntity));
        }

        return res;

    }

}