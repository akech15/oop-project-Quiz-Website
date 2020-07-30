package ge.edu.freeuni.api.converter.quiz;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.model.user.UserEntity;
import ge.edu.freeuni.server.repository.user.UserRepository;
import jdk.internal.net.http.common.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class QuizConverter {

    public static Quiz entityToQuiz(UserRepository userRepository,
                                    QuizEntity quizEntity) {
        return Quiz.builder()
                .id(quizEntity.getId())
                .name(quizEntity.getName())
                .creator(UserConverter.entityToUser(userRepository.getUserById(quizEntity.getCreatorId())))
                .description(quizEntity.getDescription())
                .creationDate(quizEntity.getCreationDate())
                .build();
    }

    public static QuizEntity quizToEntity(Quiz quiz) {

        return QuizEntity.builder()
                .id(quiz.getId())
                .creatorId(quiz.getCreator().getId())
                .name(quiz.getName())
                .creationDate(quiz.getCreationDate())
                .description(quiz.getDescription())
                .build();
    }

    public static List<Quiz> entityToQuizList(UserRepository userRepository,
                                              List<QuizEntity> allQuizzes) {
        List<Quiz> res= new ArrayList<>();
        for (QuizEntity quizEntity:
             allQuizzes) {
            res.add(QuizConverter.entityToQuiz(userRepository, quizEntity));
        }
        return res;
    }

	public static List<Pair<Quiz, Long>> entityToQuizPairList(UserRepository userRepository,
                                                              List<Pair<QuizEntity, Long>> topRatedQuizzes) {
        List<Pair<Quiz, Long> > res = new ArrayList<>();

        for (Pair<QuizEntity, Long> pair:
                topRatedQuizzes) {
            res.add(new Pair<>(QuizConverter.entityToQuiz(userRepository, pair.first), pair.second));
        }

        return res;
	}
}
