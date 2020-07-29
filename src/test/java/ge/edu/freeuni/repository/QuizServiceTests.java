package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
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
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

@SpringBootTest
public class QuizServiceTests {

    @Autowired
    private QuizService quizService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

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
    public void addQuiz() {

        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz quiz = new Quiz();
        quiz.setName("starting quiz");
        quiz.setDescription("description");
        quiz.setCreationDate(new Date());
        quiz.setCreator(user);
        Assertions.assertTrue(quizService.addQuiz(quiz));

    }

    @Test
    public void getAllQuizName() {

        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(user);
        quizService.addQuiz(quiz);

        Quiz quiz1 = new Quiz();
        quiz1.setName("quiz1");
        quiz1.setDescription("its quiz1");
        quiz1.setCreationDate(new Date());
        quiz1.setCreator(user);
        quizService.addQuiz(quiz1);

        Quiz quiz2 = new Quiz();
        quiz2.setCreator(user);
        quiz2.setName("quiz2");
        quiz2.setDescription("its quiz2");
        quiz2.setCreationDate(new Date());
        quizService.addQuiz(quiz2);

        List<String> list = quizService.getAllQuizNames();

        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("quiz", list.get(0));
        Assertions.assertEquals("quiz2", list.get(2));
    }

    @Test
    public void getQuizById() {
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(user);
        quizService.addQuiz(quiz);

        Quiz quiz1 = new Quiz();
        quiz1.setName("quiz1");
        quiz1.setDescription("its quiz1");
        quiz1.setCreationDate(new Date());
        quiz1.setCreator(user);
        quizService.addQuiz(quiz1);

        Assertions.assertEquals("quiz1", quizService.getQuizById(2).getName());
    }

    @Test
    public void getActiveQuiz() {

        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(user);
        quizService.addQuiz(quiz);

        Quiz quiz1 = new Quiz();
        quiz1.setName("quiz1");
        quiz1.setDescription("its quiz1");
        quiz1.setCreationDate(new Date());
        quiz1.setCreator(user);
        quizService.addQuiz(quiz1);

        Assertions.assertEquals("quiz1", quizService.getActiveQuiz().getName());

    }

    @Test
    public void finishMakingQuiz() {
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(user);
        quizService.addQuiz(quiz);

        Quiz quiz1 = new Quiz();
        quiz1.setName("quiz1");
        quiz1.setDescription("its quiz1");
        quiz1.setCreationDate(new Date());
        quiz1.setCreator(user);
        quizService.addQuiz(quiz1);

        quizService.finishMakingQuiz();

        Assertions.assertNull(quizService.getActiveQuiz());
    }


}
