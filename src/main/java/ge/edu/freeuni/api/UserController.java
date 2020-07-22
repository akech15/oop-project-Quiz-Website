package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        Map<String, Object> model) throws SQLException {
        model.put("username", username);

        User toCheck = new User(username, password);
        boolean wasValid = authenticationService.logIn(toCheck);

        if(wasValid){
            // Log in was successful!
            return "userPage";
        }

        /* given User is not registered in database */
        return "invalidUser";
    }

    @RequestMapping("/userhomepage")
    public String userPage(Map<String, Object> model){

        model.put("username", authenticationService.getActiveUser().getUsername());
        return "userPage";
    }

    @RequestMapping("/challengepage")
    public String challengePage(Map<String, Object> model){

        return "challenge";
    }

    @RequestMapping("/messagingpage")
    public String messagingPage(Map<String, Object> model){

        return "messages";
    }


    @RequestMapping("/friendrequestpage")
    public String friendRequestPage(Map<String, Object> model){

        return "friendRequests";
    }


    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String username, @RequestParam String password,
                                Map<String, Object> model) throws SQLException {
        model.put("username", username);
        User toAdd = new User(username, password);
        boolean wasAdded = userService.addUser(toAdd);

        /* new account created */
        if(wasAdded){
            return "index";
        }

        return "duplicateUser";
    }

}
