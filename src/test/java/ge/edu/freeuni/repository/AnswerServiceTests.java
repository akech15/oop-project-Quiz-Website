package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.api.model.passedQuiz.PassedQuiz;
import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionCategoryType;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.answer.AnswerService;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.question.QuestionService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

@SpringBootTest
public class AnswerServiceTests {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PassedQuizService passedQuizService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void clearDB() {
        StringBuilder query = new StringBuilder();
        try {
            Scanner myReader = new Scanner(new File("src/testScript.sql"));
            while (myReader.hasNextLine()) {
                query.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringTokenizer tokenizer = new StringTokenizer(query.toString(), ";");

        while (tokenizer.hasMoreTokens()) {
            jdbcTemplate.execute(tokenizer.nextToken() + ";");
        }
    }

    @Test
    public void addAnswer() {
        User toAddUser = new User();
        toAddUser.setUsername("admin");
        toAddUser.setPassword("admin");

        userService.addUser(toAddUser);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz toAddQuiz = new Quiz();
        toAddQuiz.setName("starting quiz");
        toAddQuiz.setDescription("description");
        toAddQuiz.setCreationDate(new Date());
        toAddQuiz.setCreator(user);

        quizService.startMakingQuiz(toAddQuiz);

        Quiz quiz = quizService.getQuizById(1);

        Question questionToAdd = new Question();
        questionToAdd.setCorrectAnswerIndex(-1);
        questionToAdd.setQuestion("who is the president of USA?");
        questionToAdd.setAnswers(Arrays.asList("Donald Trump", "Trump"));
        questionToAdd.setQuiz(quiz);
        questionToAdd.setType(QuestionType.QUESTION_RESPONSE);
        questionToAdd.setCategory(QuestionCategoryType.EROTIC);

        questionService.addQuestion(questionToAdd);

        Question question = questionService.getQuestionById(1);

        PassedQuiz passedQuizToAdd = new PassedQuiz();

        passedQuizToAdd.setQuiz(quiz);
        passedQuizToAdd.setUser(user);
        passedQuizToAdd.setStartDate(new Date());

        passedQuizService.startQuiz(passedQuizToAdd);

        Answer answer = new Answer();

        answer.setQuestion(question);

        answer.setUserAnswer("Trump");

        Assertions.assertTrue(answerService.addAnswer(answer));

    }

    @Test
    public void isAnswerCorrect() {
        User toAddUser = new User();
        toAddUser.setUsername("admin");
        toAddUser.setPassword("admin");

        userService.addUser(toAddUser);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz toAddQuiz = new Quiz();
        toAddQuiz.setName("starting quiz");
        toAddQuiz.setDescription("description");
        toAddQuiz.setCreationDate(new Date());
        toAddQuiz.setCreator(user);

        quizService.startMakingQuiz(toAddQuiz);

        Quiz quiz = quizService.getQuizById(1);

        Question questionToAdd = new Question();
        questionToAdd.setCorrectAnswerIndex(-1);
        questionToAdd.setQuestion("who is the president of USA?");
        questionToAdd.setAnswers(Arrays.asList("Donald Trump", "Trump"));
        questionToAdd.setQuiz(quiz);
        questionToAdd.setType(QuestionType.QUESTION_RESPONSE);
        questionToAdd.setCategory(QuestionCategoryType.EROTIC);

        questionService.addQuestion(questionToAdd);

        Question question = questionService.getQuestionById(1);

        PassedQuiz passedQuizToAdd = new PassedQuiz();

        passedQuizToAdd.setQuiz(quiz);
        passedQuizToAdd.setUser(user);
        passedQuizToAdd.setStartDate(new Date());

        passedQuizService.startQuiz(passedQuizToAdd);

        Answer answerToAdd = new Answer();
        answerToAdd.setQuestion(question);
        answerToAdd.setUserAnswer("Trump");

        Answer answerToAdd1 = new Answer();
        answerToAdd1.setQuestion(question);
        answerToAdd1.setUserAnswer("Obama");

        answerService.addAnswer(answerToAdd);
        answerService.addAnswer(answerToAdd1);

        Answer answer = answerService.getAnswerById(1);

        Answer answer1 = answerService.getAnswerById(2);

        Assertions.assertTrue(answerService.isAnswerCorrect(answer));

        Assertions.assertFalse(answerService.isAnswerCorrect(answer1));


    }

}
