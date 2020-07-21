package ge.edu.freeuni.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
public class UserController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                    Map<String, Object> model) {
        String[] arr = new String[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = "question number: " + i;
        }

        model.put("username", username);

        model.put("arr", arr);

        return "userPage";
    }

}
