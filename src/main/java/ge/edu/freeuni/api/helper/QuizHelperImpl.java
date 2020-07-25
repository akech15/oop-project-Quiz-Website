package ge.edu.freeuni.api.helper;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public final class QuizHelperImpl{

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserRepository userRepository;

    public static Quiz entityToQuiz(QuizEntity quizEntity) {
        return Quiz.builder().
                name(quizEntity.getName()).
                description(quizEntity.getDescription()).
                build();
    }

    public QuizEntity quizToEntity(Quiz quiz) {
        long creatorId = userRepository.getIdByUsername(authenticationService.getActiveUser().getUsername());
        return QuizEntity.builder().
                creatorId(creatorId).
                name(quiz.getName()).
                description(quiz.getDescription()).build();
    }
}
