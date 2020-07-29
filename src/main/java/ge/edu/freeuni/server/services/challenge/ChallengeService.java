package ge.edu.freeuni.server.services.challenge;

import ge.edu.freeuni.api.model.challenge.Challenge;
import ge.edu.freeuni.api.model.user.User;

import java.util.List;

public interface ChallengeService {

    void sendChallenge(Challenge challenge);

    void approveChallenge(Challenge challenge);

    void removeChallenge(Challenge challenge);

    List<Challenge> searchChallengesByReceiver(User receiver);

    List<Challenge> searchChallengesBySender(User sender);

}
