package ge.edu.freeuni.api;

import ge.edu.freeuni.api.converter.question.QuestionConverter;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.server.repository.quiz.QuizRepository;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.question.QuestionService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
