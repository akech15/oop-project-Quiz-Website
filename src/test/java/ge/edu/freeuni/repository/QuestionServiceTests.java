package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.question.Question;
import ge.edu.freeuni.api.model.question.QuestionCategoryType;
import ge.edu.freeuni.api.model.question.QuestionType;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
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
import java.util.*;

@SpringBootTest
public class QuestionServiceTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizService quizService;

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
    public void addQuestion(){

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

        Assertions.assertTrue(questionService.addQuestion(question));

    }

    @Test
    public void getQuestionById(){

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

        Question question1 = questionService.getQuestionById(1);

        Assertions.assertEquals("who is the president of USA?", question1.getQuestion());
    }

    @Test
    public void getAllQuestionsByQuiz(){
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

        Question question1 = new Question();
        question1.setCorrectAnswerIndex(-1);
        question1.setQuestion("who is the president of USA?");
        question1.setAnswers(Arrays.asList("Donald Trump", "Trump"));
        question1.setQuiz(quiz);
        question1.setType(QuestionType.QUESTION_RESPONSE);
        question1.setCategory(QuestionCategoryType.EROTIC);

        Question question2 = new Question();
        question2.setCorrectAnswerIndex(-1);
        question2.setQuestion("who is the president of USA?");
        question2.setAnswers(Arrays.asList("Donald Trump", "Trump"));
        question2.setQuiz(quiz);
        question2.setType(QuestionType.QUESTION_RESPONSE);
        question2.setCategory(QuestionCategoryType.EROTIC);

        questionService.addQuestion(question);
        questionService.addQuestion(question1);
        questionService.addQuestion(question2);

        Assertions.assertEquals(3, questionService.getAllQuestionsByQuiz(quiz).size());

    }

}
