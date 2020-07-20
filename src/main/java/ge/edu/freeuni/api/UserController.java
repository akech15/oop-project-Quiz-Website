package ge.edu.freeuni.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @GetMapping(path = "/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        System.out.printf("login:: username: %s, password: %s\n", username, password);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> create(@RequestParam String username, @RequestParam String password){
        System.out.printf("create:: username: %s, password: %s\n", username, password);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}
