package ge.edu.freeuni.server.services.challenge;

import ge.edu.freeuni.api.converter.challenge.ChallengeConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.challenge.Challenge;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.challenge.ChallengeRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeRepositoryImpl challengeRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private QuizRepositoryImpl quizRepository;

    @Override
    public void sendChallenge(Challenge challenge) {
        challengeRepository.
                addChallenge(ChallengeConverter.
                        challengeToEntity(challenge, userRepository));
    }

    @Override
    public void approveChallenge(Challenge challenge) {
        this.removeChallenge(challenge);
    }

    @Override
    public void removeChallenge(Challenge challenge) {
        challengeRepository.
                removeChallenge(ChallengeConverter.
                        challengeToEntity(challenge, userRepository));
    }

    @Override
    public List<Challenge> searchChallengesByReceiver(User receiver) {
        return ChallengeConverter.
                entityToChallengeList(
                        userRepository,
                        quizRepository,
                        challengeRepository.
                                searchChallengesByReceiver
                                        (UserConverter.userToEntity(receiver))
                );
    }

    @Override
    public List<Challenge> searchChallengesBySender(User sender) {
        return ChallengeConverter.
                entityToChallengeList(
                        userRepository,
                        quizRepository,
                        challengeRepository.
                                searchChallengesBySender
                                        (UserConverter.userToEntity(sender))
                );
    }

    @Override
    public Challenge getChallengeById(long challengeId) {
        return ChallengeConverter.
                entityToChallenge(challengeRepository.getChallengeById(challengeId),
                        quizRepository,
                        userRepository
                );
    }
}
