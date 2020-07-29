package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.challenge.Challenge;
import ge.edu.freeuni.api.model.quiz.Quiz;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.challenge.ChallengeService;
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
import java.util.Scanner;
import java.util.StringTokenizer;

@SpringBootTest
public class ChallengeTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChallengeService challengeService;

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
    public void approveChallengeTest() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        userService.addUser(user1);

        User sender = userService.getUserById(1);

        User user2 = new User();
        user2.setUsername("bla");
        user2.setPassword("bla");
        userService.addUser(user2);

        User receiver = userService.getUserById(2);
        authenticationService.logIn(receiver);
        authenticationService.logIn(sender);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(sender);
        quizService.startMakingQuiz(quiz);
        quizService.getActiveQuiz();

        Quiz quiz1 = quizService.getQuizById(1);

        Challenge challenge = new Challenge();
        challenge.setQuiz(quiz1);
        challenge.setReceiver(receiver);
        challenge.setSender(sender);
        challenge.setSenderScore(5);


        challengeService.sendChallenge(challenge);
        Challenge ch = challengeService.searchChallengesBySender(sender).get(0);
        challengeService.approveChallenge(ch);
        Assertions.assertEquals(0, challengeService.searchChallengesBySender(sender).size());
        Assertions.assertEquals(0, challengeService.searchChallengesByReceiver(receiver).size());

    }

    @Test

    public void removeChallenge() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        userService.addUser(user1);

        User sender = userService.getUserById(1);

        User user2 = new User();
        user2.setUsername("bla");
        user2.setPassword("bla");
        userService.addUser(user2);

        User receiver = userService.getUserById(2);
        authenticationService.logIn(receiver);
        authenticationService.logIn(sender);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(sender);
        quizService.startMakingQuiz(quiz);
        quizService.getActiveQuiz();

        Quiz quiz1 = quizService.getQuizById(1);

        Challenge challenge = new Challenge();
        challenge.setQuiz(quiz1);
        challenge.setReceiver(receiver);
        challenge.setSender(sender);
        challenge.setSenderScore(5);


        challengeService.sendChallenge(challenge);
        Challenge ch = challengeService.searchChallengesBySender(sender).get(0);
        challengeService.removeChallenge(ch);
        Assertions.assertEquals(0, challengeService.searchChallengesBySender(sender).size());
        Assertions.assertEquals(0, challengeService.searchChallengesByReceiver(receiver).size());

    }

    @Test
    public void searchChallengesTest() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        userService.addUser(user1);

        User sender = userService.getUserById(1);

        User user2 = new User();
        user2.setUsername("bla");
        user2.setPassword("bla");
        userService.addUser(user2);

        User receiver = userService.getUserById(2);
        authenticationService.logIn(receiver);
        authenticationService.logIn(sender);

        Quiz quiz = new Quiz();
        quiz.setName("quiz");
        quiz.setDescription("its quiz");
        quiz.setCreationDate(new Date());
        quiz.setCreator(sender);
        quizService.startMakingQuiz(quiz);
        quizService.getActiveQuiz();

        Quiz quiz1 = quizService.getQuizById(1);

        Challenge challenge = new Challenge();
        challenge.setQuiz(quiz1);
        challenge.setReceiver(receiver);
        challenge.setSender(sender);
        challenge.setSenderScore(5);


        challengeService.sendChallenge(challenge);

        Assertions.assertEquals("bla", challengeService.searchChallengesByReceiver(receiver).get(0).getReceiver().getUsername());
        Assertions.assertEquals("admin", challengeService.searchChallengesByReceiver(receiver).get(0).getSender().getUsername());
        Assertions.assertEquals("admin", challengeService.searchChallengesBySender(sender).get(0).getSender().getUsername());
        Assertions.assertEquals("bla", challengeService.searchChallengesBySender(sender).get(0).getReceiver().getUsername());
        Assertions.assertEquals(5, challengeService.searchChallengesBySender(sender).get(0).getSenderScore());
        Assertions.assertEquals("its quiz", challengeService.searchChallengesBySender(sender).get(0).getQuiz().getDescription());
        Assertions.assertEquals("quiz", challengeService.searchChallengesBySender(sender).get(0).getQuiz().getName());


    }

}