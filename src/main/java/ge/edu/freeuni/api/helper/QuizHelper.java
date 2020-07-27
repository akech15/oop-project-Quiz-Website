package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import org.springframework.stereotype.Component;

@Component
public final class QuizHelper {

    public static Quiz entityToQuiz(QuizEntity quizEntity) {
        return Quiz.builder().
                name(quizEntity.getName()).
                description(quizEntity.getDescription()).
                creationDate(quizEntity.getCreationDate()).
                build();
    }

    public static QuizEntity quizToEntity(AuthenticationServiceImpl authenticationService,
                                          UserRepository userRepository,
                                          Quiz quiz) {
        long creatorId = userRepository.getIdByUsername(authenticationService.getActiveUser().getUsername());
        return QuizEntity.builder().
                creatorId(creatorId).
                name(quiz.getName()).
                creationDate(quiz.getCreationDate()).
                description(quiz.getDescription()).build();
    }
}
