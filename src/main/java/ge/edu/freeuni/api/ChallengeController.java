package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.challenge.Challenge;
import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.challenge.ChallengeService;
import ge.edu.freeuni.server.services.friendship.FriendshipService;
import ge.edu.freeuni.server.services.mail.MailService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.user.UserService;
import ge.edu.freeuni.utils.Wyvili;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ChallengeController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private PassedQuizService passedQuizService;
    @Autowired
    private ChallengeService challengeService;

    @RequestMapping("/createChallenge/{quizId}")
    public String createMessage(@PathVariable long quizId,
                                Map<String, Object> model) {
        boolean valid = true;
        model.put("valid", valid);
        model.put("passedQuiz", passedQuizService.getPassedQuizById(quizId));
        model.put("status", FriendshipStatusType.APPROVED);
        return "createChallenge";
    }

    @RequestMapping("/sendChallenge/{quizId}")
    public String sendChallenge(@PathVariable long quizId,
                                @RequestParam String receiverUsername,
                                Map<String, Object> model) {

        User toSend = userService.getUserByUsername(receiverUsername);
        FriendshipStatusType friendshipStatus = friendshipService
                .getFriendshipStatus(authenticationService
                                .getActiveUser(),
                        toSend);
        boolean valid = true;
        PassedQuiz passedQuiz = passedQuizService.getPassedQuizById(quizId);
        model.put("passedQuiz", passedQuiz);
        if (toSend == null) {
            valid = false;
            model.put("valid", valid);
            model.put("status", FriendshipStatusType.STRANGERS);
            return "createChallenge";
        }
        if (!friendshipStatus.equals(FriendshipStatusType.APPROVED)) {
            model.put("valid", valid);
            model.put("status", FriendshipStatusType.STRANGERS);
            return "createChallenge";
        }
        model.put("status", FriendshipStatusType.APPROVED);
        model.put("valid", valid);
        Challenge challenge = new Challenge();
        challenge.setSender(passedQuiz.getUser());
        challenge.setReceiver(toSend);
        challenge.setQuiz(passedQuiz.getQuiz());
        challenge.setSenderScore(passedQuiz.getScore());
        challengeService.sendChallenge(challenge);
        return "createChallenge";
    }

    @RequestMapping("/challenge/{action}/{challengeId}")
    public String friendRequestAction(@PathVariable String action,
                                      @PathVariable long challengeId,
                                      Map<String, Object> model) {

        Challenge challenge = challengeService.getChallengeById(challengeId);
        if (action.equals("approve")) {
            challengeService.approveChallenge(challenge);
            Quiz toAdd = challenge.getQuiz();
            List<Wyvili<User, Long>> topRatedUsersByQuiz = quizService.getTopRatedUsersByQuiz(toAdd);
            model.put("quiz", toAdd);
            model.put("quizId", toAdd.getId());
            model.put("topRatedUsersByQuiz", topRatedUsersByQuiz);
            return "quizDescription";
        }
        challengeService.removeChallenge(challenge);
        model.put("user", authenticationService.getActiveUser());
        List<Quiz> availableQuizzes = quizService.getAllQuizzes();
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());
        List<Challenge> receivedChallenges = challengeService
                .searchChallengesByReceiver(authenticationService
                        .getActiveUser());
        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("userService", userService);
        model.put("receivedChallenges", receivedChallenges);
        return "challenge";
    }

    @RequestMapping("/challengepage")
    public String challengePage(Map<String, Object> model) {
        model.put("user", authenticationService.getActiveUser());
        List<Quiz> availableQuizzes = quizService.getAllQuizzes();
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());
        List<Challenge> receivedChallenges = challengeService
                .searchChallengesByReceiver(authenticationService
                        .getActiveUser());
        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("userService", userService);
        model.put("receivedChallenges", receivedChallenges);
        return "challenge";
    }

}
