package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepositoryImpl;
import ge.edu.freeuni.server.repository.question.QuestionRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class AnswerHelper {

    public static Answer AnswerEntityToAnswer(AnswerEntity from, QuestionRepositoryImpl questionRepository,
                                              QuizRepositoryImpl quizRepository, PassedQuizRepositoryImpl passed,
                                              UserRepositoryImpl user) {
        return Answer.builder().
                id(from.getId()).
                question(QuestionHelper.entityToQuestion(quizRepository, questionRepository.getQuestionById(from.getQuestionId()))).
                passedQuiz(PassedQuizHelper.entityToPassedQuiz(user, quizRepository, passed.getPassedQuizById(from.getPassedQuizId()))).
                userAnswer(from.getUserAnswer()).
                build();
    }

    public static AnswerEntity AnswerToEntity(Answer answer, QuizRepositoryImpl quiz) {
        return AnswerEntity.builder().
                id(answer.getId()).
                passedQuizId(answer.getPassedQuiz().getId()).
                questionId(QuestionHelper.questionToEntity(quiz, answer.getQuestion()).getId()).
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
            res.add(AnswerHelper.AnswerEntityToAnswer(answerEntity, questionRepository, quizRepository, passed, user));
        }
        return res;
    }

}
