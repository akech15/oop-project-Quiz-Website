package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.user.UserService;
import ge.edu.freeuni.utils.Wyvili;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.SQLException;
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
    private PassedQuizService passedQuizService;

    @GetMapping("/")
    public String index(Map<String, Object> model) {

        List<Wyvili<Quiz, Long>> topRatedQuizzes = quizService.getTopRatedQuizzes();

        model.put("topRatedQuizzes", topRatedQuizzes);

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

    @RequestMapping("/challengepage")
    public String challengePage(Map<String, Object> model) {
        return "challenge";
    }

    @RequestMapping("/messagingpage")
    public String messagingPage(Map<String, Object> model) {
        return "messages";
    }


    @RequestMapping("/friendrequestpage")
    public String friendRequestPage(Map<String, Object> model) {
        model.put("quizNames", quizService.getAllQuizNames());
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
        if (addUser) {
            return "index";
        }
        return "duplicateUser";
    }

//    @RequestMapping("/viewUser")
//    public String viewUser(Map<String, Object> model) {
//        return "viewUserPage";
//    }

    @RequestMapping("/logOut")
    public String logOut(){
        authenticationService.logOut();
        return "index";
    }

    @RequestMapping("/viewUserPage/{userId}")
    public String viewUserPage(@PathVariable long userId,
                               Map<String, Object> model){

        User user = userService.getUserById(userId);
        List<Quiz> availableQuizzes = quizService.getAllQuizzes();
        List<PassedQuiz> passedQuizzes = passedQuizService
                .getPassedQuizzesByUserId(userId);

        List<Quiz> userQuizzes = quizService.getQuizesByUserId(userId);

        model.put("quizzes", availableQuizzes);
        model.put("passedQuizzes", passedQuizzes);
        model.put("userQuizes", userQuizzes);
        model.put("user", user);
        return "viewUserPage";
    }


    @RequestMapping("/viewUsers")
    public String viewUsers(@RequestParam String usernameFragment,
                            Map<String, Object> model){
        List<User> usersList = userService.getUsersByUsernameFragment(usernameFragment);
        model.put("usersList", usersList);
        model.put("usernameFragment", usernameFragment);
        return "viewUsers";
    }

}
