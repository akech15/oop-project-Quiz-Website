package ge.edu.freeuni.api;

import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.quiz.QuizServiceImpl;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/quizDescriptionPage/{quizName}")
    public String aboutQuiz(@PathVariable String quizName, Map<String, Object> model) {
        return "quizDescription";
    }

    @RequestMapping("/makeQuestions")
    public String makeQuestions(Map<String, Object> model) {

        return "makeQuestions";
    }

    @RequestMapping("/multipleChoice")
    public String multipleChoice(Map<String, Object> model) {

        return "questionTypes/multipleChoice";
    }

    @RequestMapping("/multipleChoiceSubmitted")
    public String multipleChoiceSubmitted(@RequestParam String choiceCount, Map<String, Object> model) {
        model.put("choiceCount", choiceCount);
        return "questionTypes/multipleChoiceSubmitted";
    }

    @RequestMapping("/trueFalse")
    public String trueFalse(Map<String, Object> model) {

        return "questionTypes/trueFalse";
    }

    @RequestMapping("/fillBlank")
    public String fillBlank(Map<String, Object> model) {

        return "questionTypes/fillBlank";
    }


    @RequestMapping("/multipleAnswers")
    public String multipleAnswers(Map<String, Object> model) {

        return "questionTypes/multipleAnswers";
    }

    @RequestMapping("/multipleAnswersSubmitted")
    public String multipleAnswersSubmitted(@RequestParam String choiceCount, @RequestParam String answerCount,
                                           Map<String, Object> model) {
        model.put("choiceCount", choiceCount);
        model.put("answerCount", answerCount);

        return "questionTypes/multipleAnswersSubmitted";
    }

    @RequestMapping("/matching")
    public String matching(Map<String, Object> model) {
        return "questionTypes/matching";
    }
}
