package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.friendship.FriendshipService;
import ge.edu.freeuni.server.services.mail.MailService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.user.UserService;
import ge.edu.freeuni.utils.Wyvili;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class UserController {
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
    private MailService mailService;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        List<Wyvili<Quiz, Long>> topRatedQuizzes = quizService.getTopRatedQuizzes();
        List<Wyvili<User, Long>> topRatedUsers = userService.getTopRatedUsers();

        model.put("topRatedQuizzes", topRatedQuizzes);
        model.put("topRatedUsers", topRatedUsers);
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        Map<String, Object> model) {
        model.put("username", username);

        boolean validUser = authenticationService.logIn(userService.getUserByUsernameAndPassword(username, password));

        if (validUser) {

            model.put("user", authenticationService.getActiveUser());

            List<Quiz> availableQuizzes = quizService.getAllQuizzes();

            List<PassedQuiz> passedQuizzes = passedQuizService
                    .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());

            List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());

            model.put("quizzes", availableQuizzes);
            model.put("passedQuizzes", passedQuizzes);
            model.put("userQuizes", userQuizzes);
            model.put("userService", userService);

            return "userPage";
        }

        return "invalidUser";
    }

    @RequestMapping("/userhomepage")
    public String userPage(Map<String, Object> model) {

        model.put("user", authenticationService.getActiveUser());

        List<Quiz> availableQuizzes = quizService.getAllQuizzes();

        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());

        List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());

        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("userService", userService);

        return "userPage";
    }

    @RequestMapping("/messagingpage")
    public String messagingPage(Map<String, Object> model) {
        model.put("user", authenticationService.getActiveUser());
        List<Quiz> availableQuizzes = quizService.getAllQuizzes();
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());
        List<Mail> receivedMails = mailService.searchMailsByReceiver(authenticationService.getActiveUser());
        List<Mail> sentMails = mailService.searchMailsBySender(authenticationService.getActiveUser());
        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("userService", userService);
        model.put("receivedMails", receivedMails);
        model.put("sentMails", sentMails);
        return "messages";
    }


    @RequestMapping("/friendrequestpage")
    public String friendRequestPage(Map<String, Object> model) {
        model.put("user", authenticationService.getActiveUser());
        List<Quiz> availableQuizzes = quizService.getAllQuizzes();
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());
        List<User> friendRequest = friendshipService.getAllFriendRequest(authenticationService.getActiveUser());
        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("userService", userService);
        model.put("friendReq", friendRequest);
        return "friendRequests";
    }

    @RequestMapping("/friendrequest/{action}/{userId}")
    public String friendRequestAction(@PathVariable String action,
                                      @PathVariable long userId,
                                      Map<String, Object> model) {

        if(action.equals("approve")){
            friendshipService.approveRequest(authenticationService.getActiveUser().getId(), userId);
            friendshipService.approveRequest(userId, authenticationService.getActiveUser().getId());


        }else{
            friendshipService.cancelRequest(authenticationService.getActiveUser(), userService.getUserById(userId));
        }

        model.put("user", authenticationService.getActiveUser());
        List<Quiz> availableQuizzes = quizService.getAllQuizzes();
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(authenticationService.getActiveUser().getId());
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(authenticationService.getActiveUser().getId());
        List<User> friendRequest = friendshipService.getAllFriendRequest(authenticationService.getActiveUser());
        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("userService", userService);
        model.put("friendReq", friendRequest);

        return "friendRequests";
    }


    @RequestMapping("/createAccount")
    public String createAccount(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String name,
                                Map<String, Object> model) {
        model.put("username", username);
        User toAdd = new User();
        toAdd.setUsername(username);
        toAdd.setPassword(password);
        toAdd.setName(name);
        boolean addUser = userService.addUser(toAdd);
        List<Wyvili<Quiz, Long>> topRatedQuizzes = quizService.getTopRatedQuizzes();
        List<Wyvili<User, Long>> topRatedUsers = userService.getTopRatedUsers();

        model.put("topRatedQuizzes", topRatedQuizzes);
        model.put("topRatedUsers", topRatedUsers);
        if (addUser) {
            return "index";
        }
        return "duplicateUser";
    }

    @RequestMapping("/logOut")
    public String logOut(Map<String, Object> model) {
        authenticationService.logOut();

        List<Wyvili<Quiz, Long>> topRatedQuizzes = quizService.getTopRatedQuizzes();
        List<Wyvili<User, Long>> topRatedUsers = userService.getTopRatedUsers();

        model.put("topRatedQuizzes", topRatedQuizzes);
        model.put("topRatedUsers", topRatedUsers);

        return "index";
    }

    @RequestMapping("/viewUserPage/{userId}")
    public String viewUserPage(@PathVariable long userId,
                               Map<String, Object> model) {

        User user = userService.getUserById(userId);
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(userId);
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(userId);
        FriendshipStatusType friendship = friendshipService
                                          .getFriendshipStatus(authenticationService
                                                               .getActiveUser(), user);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizzes", userQuizzes);
        model.put("user", user);
        model.put("friendShipType", friendship);
        model.put("activeUsrId", authenticationService.getActiveUser().getId());
        return "viewUserPage";
    }

    @RequestMapping("/friendRequest/{userId}/{action}")
    public String friendRequest(@PathVariable long userId, @PathVariable String action,
                                Map<String, Object> model) {

        User user = userService.getUserById(userId);
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(userId);
        List<Quiz> userQuizzes = quizService.getQuizesByUserId(userId);

        if (action.equals("cancel")) {
            friendshipService.removeRequest(authenticationService.getActiveUser(), user);
        } else if (action.equals("remove")) {
            friendshipService.removeFriend(authenticationService.getActiveUser(), user);
        } else {
            friendshipService.sendRequest(authenticationService.getActiveUser(), user);
        }

        FriendshipStatusType friendship = friendshipService.getFriendshipStatus(authenticationService.getActiveUser(), user);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizzes", userQuizzes);
        model.put("user", user);
        model.put("friendShipType", friendship);
        model.put("activeUsrId", authenticationService.getActiveUser().getId());
        return "viewUserPage";
    }

    @RequestMapping("/viewUsers")
    public String viewUsers(@RequestParam String usernameFragment,
                            Map<String, Object> model) {
        List<User> usersList = userService.getUsersByUsernameFragment(usernameFragment);
        model.put("usersList", usersList);
        model.put("usernameFragment", usernameFragment);
        return "viewUsers";
    }



}
