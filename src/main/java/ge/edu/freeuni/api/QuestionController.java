package ge.edu.freeuni.api;

import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionCategoryType;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.server.services.authentication.AuthenticationServiceImpl;
import ge.edu.freeuni.server.services.question.QuestionServiceImpl;
import ge.edu.freeuni.server.services.quiz.QuizServiceImpl;
import ge.edu.freeuni.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping("/getType/{type}")
    public String getQuestionType(@PathVariable String type,
                                  Map<String, Object> model) {

        return QuestionConverter.getJspFromType(type);
    }

//    @RequestMapping("/addQuestion/questionResponse")
//    public String addQuestionResponse(Map<String, Object> model) {
//        addQuestionResponseAndFillInTheBlank(model);
//        return "getType";
//    }
//
//    @RequestMapping("/addQuestion/fillInTheBlank")
//    public String addFillInTheBlank(Map<String, Object> model) {
//        addQuestionResponseAndFillInTheBlank(model);
//        return "getType";
//    }

//    @RequestMapping("/addQuestion/multipleChoice")
//    public String addMultipleChoice(Map<String, Object> model) {
//        String question = (String) model.get("question");
//
//        String answer1 = (String) model.get("answer #1");
//        String answer2 = (String) model.get("answer #2");
//        String answer3 = (String) model.get("answer #3");
//        String answer4 = (String) model.get("answer #4");
//
//        String category = (String) model.get("category");
//        long correctAnswerIndex = (Long) model.get("correct answer category");
//
//        questionService.addQuestion(
//                new Question(
//                        -1,
//                        quizService.getActiveQuiz(),
//                        question,
//                        QuestionType.MULTIPLE_CHOICE,
//                        correctAnswerIndex,
//                        new ArrayList<>(Arrays.asList(answer1, answer2, answer3, answer4)),
//                        Enum.valueOf(QuestionCategoryType.class, category),
//                        null
//                )
//        );
//
//        return "getType";
//    }
//
//    @RequestMapping("/addQuestion/pictureResponse")
//    public String addPictureResponse(Map<String, Object> model) {
//        String question = (String) model.get("question");
//        String answer = (String) model.get("answer");
//        String category = (String) model.get("category");
//        String pictureURL = (String) model.get("picture url");
//
//        questionService.addQuestion(
//                new Question(
//                        -1,
//                        quizService.getActiveQuiz(),
//                        question,
//                        QuestionType.PICTURE_RESPONSE,
//                        -1,
//                        StringUtils.stringToList(answer, ','),
//                        Enum.valueOf(QuestionCategoryType.class, category),
//                        pictureURL
//                )
//        );
//
//        return "getType";
//    }

    @RequestMapping("/addQuestion/finish")
    public String finishQuizMaking(Map<String, Object> model) {
        model.put("username", authenticationService.getActiveUser().getUsername());
        model.put("quizNames", quizService.getAllQuizNames());
        return "userPage";
    }
//
//    private void addQuestionResponseAndFillInTheBlank(Map<String, Object> model) {
//        String question = (String) model.get("question");
//        String answer = (String) model.get("answer");
//        String category = (String) model.get("category");
//
//        questionService.addQuestion(
//                new Question(
//                        -1,
//                        quizService.getActiveQuiz(),
//                        question,
//                        QuestionType.QUESTION_RESPONSE,
//                        -1,
//                        StringUtils.stringToList(answer, ','),
//                        Enum.valueOf(QuestionCategoryType.class, category),
//                        null
//                )
//        );
//    }


    @RequestMapping("/addMultipleChoice")
    public String addMultipleChoiceQuestion(@RequestParam String question, @RequestParam Map<String, String> params, Map<String, Object> model){

        List<String> answers = new ArrayList<>();

        for(int i = 0; i < params.size() - 1; i++){
            int toAppend = i + 1;
            String toGet = "choice"+toAppend;
            String toAdd = params.get(toGet);
            answers.add(toAdd);
        }

        String correctAnswer = params.get("correctAnswer");
        Question addedQuestion = new Question();
        addedQuestion.setQuestion(question);
        addedQuestion.setAnswers(answers);
        addedQuestion.setType(QuestionType.MULTIPLE_CHOICE);
        addedQuestion.setCorrectAnswer(correctAnswer);
        addedQuestion.setCorrectAnswerIndex(correctAnswer.charAt(0) - 'a');
        questionService.addQuestion(addedQuestion);
        return "makeQuestions";
    }

    @RequestMapping("/addFillInBlankQuestion")
    public String addFillInBlankQuestion(@RequestParam String firstPart, @RequestParam String secondPart,
                                         @RequestParam String blank,
                                         Map<String, Object> model){

        String question = firstPart + "_" + secondPart;
        String correctAnswer = blank;

        Question toAdd = new Question();
        toAdd.setQuestion(question);
        toAdd.setType(QuestionType.FILL_IN_THE_BLANK);
        toAdd.setCorrectAnswer(correctAnswer);
        toAdd.setCorrectAnswerIndex(-1);
        toAdd.setAnswers(new ArrayList<>());
        questionService.addQuestion(toAdd);

        return "makeQuestions";
    }

}
