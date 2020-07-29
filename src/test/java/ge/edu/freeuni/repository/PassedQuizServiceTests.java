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
public class PassedQuizServiceTests {

    @Autowired
    private PassedQuizService passedQuizService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthenticationService authenticationService;

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
    public void startQuiz() {

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

        Question question = new Question();
        question.setCorrectAnswerIndex(-1);
        question.setQuestion("who is the president of USA?");
        question.setAnswers(Arrays.asList("Donald Trump", "Trump"));
        question.setQuiz(quiz);
        question.setType(QuestionType.QUESTION_RESPONSE);
        question.setCategory(QuestionCategoryType.EROTIC);

        questionService.addQuestion(question);

        PassedQuiz passedQuizToAdd = new PassedQuiz();

        passedQuizToAdd.setQuiz(quiz);
        passedQuizToAdd.setUser(user);
        passedQuizToAdd.setStartDate(new Date());

        Assertions.assertTrue(passedQuizService.startQuiz(passedQuizToAdd));

    }

    @Test
    public void finishQuiz() {
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

        PassedQuiz passedQuiz = passedQuizService.getPassedQuizById(1);

        Answer answerToAdd = new Answer();

        answerToAdd.setQuestion(question);
        answerToAdd.setPassedQuiz(passedQuiz);
        answerToAdd.setUserAnswer("Trump");

        answerService.addAnswer(answerToAdd);

        Assertions.assertEquals(1, passedQuizService.finishQuiz().getScore());
    }

    @Test
    public void getPassedQuizById() {
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

        Question question = new Question();
        question.setCorrectAnswerIndex(-1);
        question.setQuestion("who is the president of USA?");
        question.setAnswers(Arrays.asList("Donald Trump", "Trump"));
        question.setQuiz(quiz);
        question.setType(QuestionType.QUESTION_RESPONSE);
        question.setCategory(QuestionCategoryType.EROTIC);

        questionService.addQuestion(question);

        PassedQuiz passedQuizToAdd = new PassedQuiz();

        passedQuizToAdd.setQuiz(quiz);
        passedQuizToAdd.setUser(user);
        passedQuizToAdd.setStartDate(new Date());

        passedQuizService.startQuiz(passedQuizToAdd);

        PassedQuiz passedQuiz = passedQuizService.getPassedQuizById(1);

        Assertions.assertEquals("admin", passedQuiz.getUser().getUsername());
        Assertions.assertEquals("starting quiz", passedQuiz.getQuiz().getName());
    }

    @Test
    public void getPassedQuizScore() {
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

        PassedQuiz passedQuiz = passedQuizService.getPassedQuizById(1);

        Answer answerToAdd = new Answer();

        answerToAdd.setQuestion(question);
        answerToAdd.setPassedQuiz(passedQuiz);
        answerToAdd.setUserAnswer("Trump");

        answerService.addAnswer(answerToAdd);

        Assertions.assertEquals(1, passedQuizService.getPassedQuizScore(passedQuiz));

    }


}
