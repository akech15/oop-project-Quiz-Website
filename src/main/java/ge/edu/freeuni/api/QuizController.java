package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.quiz.QuizService;
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
    private QuizService quizService;

    @Autowired
    private AuthenticationService authenticationService;

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

    @RequestMapping("/startQuizMaking")
    public String startQuizMaking(@RequestParam String quizName, @RequestParam String description,
                                  Map<String, Object> model) {
        String name = quizName;
        quizService.startMakingQuiz(new Quiz(name, description));
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

    @RequestMapping("/questionResponse")
    public String questionResponse(Map<String, Object> model){

        return "questionTypes/questionResponse";
    }


    @RequestMapping("/questionResponseSubmitted")
    public String questionResponseSubmitted(@RequestParam String choiceCount, Map<String, Object> model){
        model.put("choiceCount", choiceCount);

        return "questionTypes/questionResponseSubmitted";
    }


    @RequestMapping("/matching")
    public String matching(Map<String, Object> model) {
        return "questionTypes/matching";
    }

    @RequestMapping("/finishQuiz")
    public String finishQuiz(Map<String, Object> model){
        quizService.finishMakingQuiz();
        return "finishedQuiz";
    }

}
