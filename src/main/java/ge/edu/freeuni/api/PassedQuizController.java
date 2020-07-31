package ge.edu.freeuni.api;

import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.services.answer.AnswerService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.question.QuestionService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PassedQuizController {

    @Autowired
    private PassedQuizService passedQuizService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/startPlayingQuiz/{quizId}")
    public String startPlayingQuiz(@PathVariable long quizId, Map<String, Object> model){

        Quiz quiz = quizService.getQuizById(quizId);
        PassedQuiz passedQuiz = new PassedQuiz();
        passedQuiz.setQuiz(quiz);
        passedQuizService.startQuiz(passedQuiz);

        List<Question> questions = questionService.getAllQuestionsByQuiz(quiz);

        model.put("question", questions.get(0));
        model.put("index", (long)(0));
        model.put("quizId", quizId);

        return QuestionConverter.getJspFromType(questions.get(0).getType());
    }

    @RequestMapping("questionsWrapper/{index_of_question}/{quizId}")
    public String answerHandler(@RequestParam Map<String, Object> params,
                                @PathVariable long quizId,
                                @PathVariable long index_of_question,
                                Map<String, Object> model){


        List<Question> questions = questionService.getAllQuestionsByQuiz(quizService.getQuizById(quizId));

        Question currQuestion = questions.get((int) index_of_question);
        String userAnswer = (String) params.get("correctAnswer");

        Answer answer = new Answer();

        answer.setPassedQuiz(passedQuizService.getActivePassedQuiz());
        answer.setQuestion(currQuestion);
        answer.setUserAnswer(userAnswer);

        answerService.addAnswer(answer);

        if(index_of_question >= questions.size() - 1){

            PassedQuiz passedQuiz = passedQuizService.finishQuiz();
            model.put("passedQuiz", passedQuiz);

            return "finishPlayingQuiz";
        }

        Question nextQuestion = questions.get((int) (index_of_question + 1));

        model.put("quizId", quizId);
        model.put("question", nextQuestion);
        model.put("index", index_of_question + 1);

        return QuestionConverter.getJspFromType(nextQuestion.getType());
    }


}
