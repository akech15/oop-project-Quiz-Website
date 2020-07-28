package ge.edu.freeuni.api.converter.challenge;

import ge.edu.freeuni.api.converter.quiz.QuizConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.challenge.Challenge;
import ge.edu.freeuni.server.model.challenge.ChallengeEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

public final class ChallengeConverter {

    public static Challenge entityToChallenge(ChallengeEntity from, QuizRepositoryImpl quizRepository,
                                              UserRepositoryImpl user) {
        return Challenge.builder().
                id(from.getId()).
                senderScore(from.getSenderScore()).
                quiz(QuizConverter.entityToQuiz(quizRepository.getQuizById(from.getQuizId()))).
                receiver(UserConverter.entityToUser(user.getUserById(from.getReceiverId()))).
                sender(UserConverter.entityToUser(user.getUserById(from.getSenderId()))).
                build();
    }

    public static ChallengeEntity challengeToEntity(Challenge from, QuizRepositoryImpl quizRepository,
                                                    UserRepositoryImpl user) {
        return ChallengeEntity.builder().
                id(from.getId()).
                senderScore(from.getSenderScore()).
                quizId(quizRepository.getQuizByName(from.getQuiz().getName()).getId()).
                receiverId(user.getIdByUsername(from.getReceiver().getUsername())).
                senderId(user.getIdByUsername(from.getSender().getUsername())).
                build();
    }

}
