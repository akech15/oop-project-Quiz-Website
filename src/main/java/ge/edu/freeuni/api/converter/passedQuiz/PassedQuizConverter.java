package ge.edu.freeuni.api.converter.passedQuiz;

import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;
import ge.edu.freeuni.server.model.question.QuestionEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public final class PassedQuizConverter {

    public static PassedQuiz entityToPassedQuiz(UserRepository userRepository,
                                                QuizRepository quizRepository,
                                                PassedQuizEntity passedQuizEntity) {
        return PassedQuiz.builder()
                .id(passedQuizEntity.getId())
                .user(UserConverter.entityToUser(userRepository.getUserById(passedQuizEntity.getUserId())))
                .quiz(QuizConverter.entityToQuiz(userRepository,
                        quizRepository.getQuizById(passedQuizEntity.getQuizId())))
                .score(passedQuizEntity.getScore())
                .startDate(passedQuizEntity.getStartDate())
                .endDate(passedQuizEntity.getEndDate())
                .duration(passedQuizEntity.getDuration())
                .build();
    }

    public static PassedQuizEntity passedQuizToEntity(QuizRepository quizRepository,
                                                      PassedQuiz passedQuiz) {
        return PassedQuizEntity.builder()
                .id(passedQuiz.getId())
                .userId(passedQuiz.getUser().getId())
                .quizId(quizRepository.getQuizById(passedQuiz.getQuiz().getId()).getId())
                .score(passedQuiz.getScore())
                .startDate(passedQuiz.getStartDate())
                .endDate(passedQuiz.getEndDate())
                .duration(passedQuiz.getDuration())
                .build();
    }


    public static List<PassedQuiz> entityToPassedQuizList(UserRepository userRepository,
                                                    QuizRepository quizRepository,
                                                    List<PassedQuizEntity> passedQuizzes) {
        List<PassedQuiz> res = new ArrayList<>();

        for (PassedQuizEntity passedQuizEntity :
                passedQuizzes) {
            res.add(PassedQuizConverter.entityToPassedQuiz(userRepository, quizRepository, passedQuizEntity));
        }

        return res;
    }
}
