package ge.edu.freeuni.api.converter.passedQuiz;

import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.server.model.passedQuiz.PassedQuizEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

public final class PassedQuizConverter {

    public static PassedQuiz entityToPassedQuiz(UserRepositoryImpl userRepository,
                                                QuizRepositoryImpl quizRepository,
                                                PassedQuizEntity passedQuizEntity) {
        return PassedQuiz.builder().
                id(passedQuizEntity.getId()).
                user(UserConverter.entityToUser(userRepository.getUserById(passedQuizEntity.getUserId()))).
                quiz(QuizConverter.entityToQuiz(quizRepository.getQuizById(passedQuizEntity.getQuizId()))).
                score(passedQuizEntity.getScore()).
                startDate(passedQuizEntity.getStartDate()).
                endDate(passedQuizEntity.getEndDate()).
                duration(passedQuizEntity.getDuration()).build();
    }

    public static PassedQuizEntity passedQuizToEntity(UserRepositoryImpl userRepository,
                                                      QuizRepositoryImpl quizRepository,
                                                      PassedQuiz passedQuiz) {
        return PassedQuizEntity.builder().
                id(passedQuiz.getId()).
                userId(userRepository.getIdByUsername(passedQuiz.getUser().getUsername())).
                quizId(quizRepository.getQuizByName(passedQuiz.getQuiz().getName()).getId()).
                score(passedQuiz.getScore()).
                startDate(passedQuiz.getStartDate()).
                endDate(passedQuiz.getEndDate()).
                duration(passedQuiz.getDuration()).build();
    }

}
