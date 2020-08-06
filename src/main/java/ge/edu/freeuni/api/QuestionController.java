package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.question.QuestionServiceImpl;
import ge.edu.freeuni.server.services.quiz.QuizServiceImpl;
import ge.edu.freeuni.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;


    @RequestMapping("/addQuestion/finish")
    public String finishQuizMaking(Map<String, Object> model) {
        model.put("username", authenticationService.getActiveUser().getUsername());
        model.put("quizNames", quizService.getAllQuizNames());
        return "userPage";
    }


    @RequestMapping("/addMultipleChoice")
    public String addMultipleChoiceQuestion(@RequestParam String question, @RequestParam Map<String, String> params, Map<String, Object> model) {

        List<String> choices = new ArrayList<>();

        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "choice" + toAppend;
            String toAdd = params.get(toGet);
            choices.add(toAdd);
        }

        String correctAnswer = params.get("correctAnswer");
        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setChoices(choices);
        addedQuestion.setType(QuestionType.MULTIPLE_CHOICE);
        addedQuestion.setCorrectAnswer("ar aqvs mnishvneloba ra agdia");
        addedQuestion.setCorrectAnswerIndex(correctAnswer.charAt(0) - 'a');
        questionService.addQuestion(addedQuestion);
        return "makeQuestions";
    }

    @RequestMapping("/addFillInBlankQuestion")
    public String addFillInBlankQuestion(@RequestParam String firstPart, @RequestParam String secondPart,
                                         @RequestParam String blank,
                                         Map<String, Object> model) {

        String question = firstPart + "_" + secondPart;
        String correctAnswer = blank;

        Question toAdd = new Question();
        toAdd.setQuestion(question);
        toAdd.setType(QuestionType.FILL_IN_THE_BLANK);
        toAdd.setCorrectAnswer(correctAnswer);
        toAdd.setCorrectAnswerIndex(-1);
        toAdd.setChoices(new ArrayList<>());
        questionService.addQuestion(toAdd);

        return "makeQuestions";
    }

    @RequestMapping("/addQuestionResponse")
    public String addQuestionResponse(@RequestParam Map<String, String> params, Map<String, Object> model) {
        String question = params.get("question");
        List<String> answers = new ArrayList<>();

        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "choice" + toAppend;
            String toAdd = params.get(toGet);
            answers.add(toAdd);
        }
        System.out.println(question);
        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setChoices(new ArrayList<>());
        addedQuestion.setType(QuestionType.QUESTION_RESPONSE);
        addedQuestion.setCorrectAnswer(StringUtils.listToString(answers, ','));
        addedQuestion.setCorrectAnswerIndex(-1);
        questionService.addQuestion(addedQuestion);


        return "makeQuestions";
    }

    @RequestMapping("/addMultipleAnswers")
    public String addMultipleAnswers(@RequestParam Map<String, String> params, Map<String, Object> model) {
        String question = params.get("question");
        List<String> answers = new ArrayList<>();
        String correctAnswer = "";
        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "answer" + toAppend;
            String toAdd = params.get(toGet);
            if (toAdd != null)
                correctAnswer += toAdd + ",";
        }

        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "choice" + toAppend;
            String toAdd = params.get(toGet);
            if (toAdd != null)
                answers.add(toAdd);
        }

        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setChoices(answers);
        addedQuestion.setType(QuestionType.MULTIPLE_ANSWERS);
        addedQuestion.setCorrectAnswer(correctAnswer);
        addedQuestion.setCorrectAnswerIndex(-1);
        questionService.addQuestion(addedQuestion);


        return "makeQuestions";
    }

    @RequestMapping("/addTrueFalse")
    public String addTrueFalse(@RequestParam Map<String, String> params, Map<String, Object> model) {
        String question = params.get("question");
        boolean isTrue = (params.get("trueCheckBox") != null);
        String correctAnswer;
        if (isTrue)
            correctAnswer = "True";
        else
            correctAnswer = "False";
        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setChoices(new ArrayList<>());
        addedQuestion.setType(QuestionType.TRUE_FALSE);
        addedQuestion.setCorrectAnswer(correctAnswer);
        addedQuestion.setCorrectAnswerIndex(-1);
        questionService.addQuestion(addedQuestion);

        return "makeQuestions";
    }

    @RequestMapping("/addMultipleBLanks")
    public String addMultipleBLanks(@RequestParam Map<String, String> params, Map<String, Object> model) {
        String question = "";
        List<String> answers = new ArrayList<>();
        String correctAnswer = "";
        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "answer" + toAppend;
            String toAdd = params.get(toGet);
            if (toAdd != null)
                correctAnswer += toAdd + ",";
        }


        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "choice" + toAppend;
            String toAdd = params.get(toGet);
            if (toAdd != null) {
                answers.add(toAdd);
                question += toAdd + "_";
            }
        }

        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setChoices(answers);
        addedQuestion.setType(QuestionType.MULTIPLE_BLANKS);
        addedQuestion.setCorrectAnswer(correctAnswer);
        addedQuestion.setCorrectAnswerIndex(-1);
        questionService.addQuestion(addedQuestion);

        return "makeQuestions";
    }

    @RequestMapping("/addImageAnswer")
    public String addImageAnswer(@RequestParam Map<String, String> params, Map<String, Object> model) {
        String question = params.get("question");
        List<String> answers = new ArrayList<>();

        for (int i = 0; i < params.size() - 1; i++) {
            int toAppend = i + 1;
            String toGet = "choice" + toAppend;
            String toAdd = params.get(toGet);
            answers.add(toAdd);
        }
        System.out.println(question);
        String imageURL = params.get("imageURL");
        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setChoices(new ArrayList<>());
        addedQuestion.setType(QuestionType.PICTURE_RESPONSE);
        addedQuestion.setCorrectAnswer(StringUtils.listToString(answers, ','));
        addedQuestion.setCorrectAnswerIndex(-1);
        addedQuestion.setPictureURL(imageURL);
        questionService.addQuestion(addedQuestion);


        return "makeQuestions";
    }


}
