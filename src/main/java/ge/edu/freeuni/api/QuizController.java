package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.quiz.QuizServiceImpl;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class QuizController {

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/createNewQuiz")
    public String createQuiz(Map<String, Object> model) {
        return "createQuiz";
    }

    @RequestMapping("/preQuiz")
    public String startQuiz(Map<String, Object> model) {
        return "preQuizPage";
    }

    @RequestMapping("/quizDescriptionPage/{quizId}")
    public String aboutQuiz(@PathVariable long quizId, Map<String, Object> model) {
        // TODO !!!!!!!!!!!!!!!!!!!!!!!!!!! quizId is nacvlad quizName ewera,
        // TODO jsp -dan id unda gamovatanot
        Quiz quiz = quizService.getQuizById(quizId);
        model.put("quiz", quiz);
        return "quizDescription";
    }

}
