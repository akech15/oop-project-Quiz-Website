package ge.edu.freeuni.server.repository.challenge;


import ge.edu.freeuni.server.model.challenge.ChallengeEntity;
import ge.edu.freeuni.server.model.user.UserEntity;

import java.util.List;

public interface ChallengeRepository {

    ChallengeEntity getChallengeById(long id);

    void addChallenge(ChallengeEntity challengeEntity);

    void removeChallenge(ChallengeEntity challengeEntity);

    List<ChallengeEntity> searchChallengesByReceiver(UserEntity receiver);

    List<ChallengeEntity> searchChallengesBySender(UserEntity sender);

}
