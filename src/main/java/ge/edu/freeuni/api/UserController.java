package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.quiz.QuizServiceImpl;
import ge.edu.freeuni.server.services.user.UserService;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        Map<String, Object> model) throws SQLException {
        model.put("username", username);

        boolean validUser = authenticationService.logIn(userService.getUserByUsername(username));

        if (validUser) {
            List<Quiz> quizList = quizService.getAllQuizzes();
            model.put("quizzes", quizList);
            return "userPage";
        }


        return "invalidUser";
    }

    @RequestMapping("/userhomepage")
    public String userPage(Map<String, Object> model) {
        model.put("username", authenticationService.getActiveUser().getUsername());

        List<Quiz> quizList = quizService.getAllQuizzes();
        model.put("quizzes", quizList);

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
    public String createAccount(@RequestParam String username, @RequestParam String password,
                                Map<String, Object> model) throws SQLException {
        model.put("username", username);
        User toAdd = new User();
        toAdd.setUsername(username);
        toAdd.setPassword(password);
        boolean addUser = userService.addUser(toAdd);
        if (addUser) {
            return "index";
        }
        return "duplicateUser";
    }

    @RequestMapping("/viewUser")
    public String viewUser(Map<String, Object> model){
        return "viewUserPage";
    }
}
