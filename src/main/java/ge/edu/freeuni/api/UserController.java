package ge.edu.freeuni.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @GetMapping(path = "/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "login";
    }

}
