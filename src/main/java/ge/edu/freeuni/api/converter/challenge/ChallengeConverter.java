package ge.edu.freeuni.api.converter.challenge;

import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.challenge.Challenge;
import ge.edu.freeuni.server.model.challenge.ChallengeEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class ChallengeConverter {

    public static Challenge entityToChallenge(ChallengeEntity from,
                                              QuizRepositoryImpl quizRepository,
                                              UserRepositoryImpl userRepository) {
        return Challenge.builder().
                id(from.getId()).
                senderScore(from.getSenderScore()).
                quiz(QuizConverter.entityToQuiz(userRepository,
                        quizRepository.getQuizById(from.getQuizId()))).
                receiver(UserConverter.entityToUser(userRepository.getUserById(from.getReceiverId()))).
                sender(UserConverter.entityToUser(userRepository.getUserById(from.getSenderId()))).
                build();
    }

    public static ChallengeEntity challengeToEntity(Challenge challenge,
                                                    UserRepositoryImpl userRepository) {
        return ChallengeEntity.builder().
                id(challenge.getId()).
                senderScore(challenge.getSenderScore()).
                quizId(challenge.getQuiz().getId()).
                receiverId(challenge.getReceiver().getId()).
                senderId(challenge.getSender().getId()).
                build();
    }

    public static List<Challenge> entityToChallengeList(UserRepositoryImpl userRepository,
                                              QuizRepositoryImpl quizRepository,
                                              List<ChallengeEntity> list){

        List<Challenge> res = new ArrayList<>();
        for (ChallengeEntity challengeEntity:
                list) {
            res.add(ChallengeConverter.
                    entityToChallenge(challengeEntity, quizRepository,userRepository));
        }
        return res;

    }

}
