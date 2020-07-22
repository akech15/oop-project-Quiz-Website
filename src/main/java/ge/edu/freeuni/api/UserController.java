package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String username, @RequestParam String password,
                                Map<String, Object> model) throws SQLException {
        model.put("username", username);
        User toAdd = new User(username, password);
        boolean wasAdded = userService.addUser(toAdd);
        if(wasAdded){
            return "index";
        }
            return "duplicateUser";
    }

}
