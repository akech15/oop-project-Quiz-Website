package ge.edu.freeuni.api.converter.answer;

import ge.edu.freeuni.api.converter.passedQuiz.PassedQuizConverter;
import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepositoryImpl;
import ge.edu.freeuni.server.repository.question.QuestionRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class AnswerConverter {

    public static Answer AnswerEntityToAnswer(AnswerEntity from, QuestionRepositoryImpl questionRepository,
                                              QuizRepositoryImpl quizRepository, PassedQuizRepositoryImpl passed,
                                              UserRepositoryImpl user) {
        return Answer.builder().
                id(from.getId()).
                question(QuestionConverter.entityToQuestion(user,
                        quizRepository, questionRepository.getQuestionById(from.getQuestionId()))).
                passedQuiz(PassedQuizConverter.entityToPassedQuiz(user, quizRepository,
                                                    passed.getPassedQuizById(from.getPassedQuizId()))).
                userAnswer(from.getUserAnswer()).
                build();
    }

    public static AnswerEntity AnswerToEntity(Answer answer, QuizRepositoryImpl quiz) {
        return AnswerEntity.builder().
                id(answer.getId()).
                passedQuizId(answer.getPassedQuiz().getId()).
                questionId(QuestionConverter.questionToEntity(quiz, answer.getQuestion()).getId()).
                userAnswer(answer.getUserAnswer()).
                build();

    }

    public static List<Answer> entityToAnswerList(QuestionRepositoryImpl questionRepository,
                                    QuizRepositoryImpl quizRepository,
                                    PassedQuizRepositoryImpl passed,
                                    UserRepositoryImpl user,
                                    List<AnswerEntity> list){
        List<Answer> res = new ArrayList<>();
        for (AnswerEntity answerEntity:
             list) {
            res.add(AnswerConverter.AnswerEntityToAnswer(answerEntity, questionRepository, quizRepository, passed, user));
        }
        return res;
    }

}
