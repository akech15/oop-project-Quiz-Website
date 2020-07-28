package ge.edu.freeuni.api.converter.answer;

import ge.edu.freeuni.api.converter.passedQuiz.PassedQuizConverter;
import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepository;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepositoryImpl;
import ge.edu.freeuni.server.repository.question.QuestionRepository;
import ge.edu.freeuni.server.repository.question.QuestionRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class AnswerConverter {

    public static Answer AnswerEntityToAnswer(AnswerEntity from, QuestionRepository questionRepository,
                                              QuizRepository quizRepository, PassedQuizRepository passed,
                                              UserRepository userRepository) {
        return Answer.builder().
                id(from.getId()).
                question(QuestionConverter.entityToQuestion(userRepository,
                        quizRepository, questionRepository.getQuestionById(from.getQuestionId()))).
                passedQuiz(PassedQuizConverter.entityToPassedQuiz(userRepository, quizRepository,
                                                    passed.getPassedQuizById(from.getPassedQuizId()))).
                userAnswer(from.getUserAnswer()).
                build();
    }

    public static AnswerEntity AnswerToEntity(Answer answer, QuizRepository quiz) {
        return AnswerEntity.builder().
                id(answer.getId()).
                passedQuizId(answer.getPassedQuiz().getId()).
                questionId(QuestionConverter.questionToEntity(quiz, answer.getQuestion()).getId()).
                userAnswer(answer.getUserAnswer()).
                build();

    }

    public static List<Answer> entityToAnswerList(QuestionRepository questionRepository,
                                                  QuizRepository quizRepository,
                                                  PassedQuizRepository passed,
                                                  UserRepository user,
                                                  List<AnswerEntity> list){
        List<Answer> res = new ArrayList<>();
        for (AnswerEntity answerEntity:
             list) {
            res.add(AnswerConverter.AnswerEntityToAnswer(answerEntity, questionRepository, quizRepository, passed, user));
        }
        return res;
    }

}
