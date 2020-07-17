package ge.edu.freeuni.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @GetMapping(path = "/login")
    public void login(@RequestParam String username, @RequestParam String password) {
        System.out.println("asdadadada");
        System.out.printf("username: %s, password: %s\n", username, password);
    }

}
