package ge.edu.freeuni.api.converter.quiz;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public final class QuizConverter {

    public static Quiz entityToQuiz(UserRepository userRepository,
                                    QuizEntity quizEntity) {
        return Quiz.builder().
                id(quizEntity.getId()).
                name(quizEntity.getName()).
                creator(UserConverter.entityToUser(userRepository.getUserById(quizEntity.getCreatorId()))).
                description(quizEntity.getDescription()).
                creationDate(quizEntity.getCreationDate()).
                build();
    }

    public static QuizEntity quizToEntity(Quiz quiz) {

        return QuizEntity.builder().
                id(quiz.getId()).
                creatorId(quiz.getCreator().getId()).
                name(quiz.getName()).
                creationDate(quiz.getCreationDate()).
                description(quiz.getDescription()).build();
    }
}
