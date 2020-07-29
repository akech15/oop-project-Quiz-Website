package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.AsyncRestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

@SpringBootTest
public class AuthenticationServiceTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

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
    public void logInTest(){
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        Assertions.assertTrue(authenticationService.logIn(user));
        Assertions.assertFalse(authenticationService.logIn(new User(-1,"user", "user")));
    }

    @Test
    public void getActiveUserTest(){
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Assertions.assertEquals("admin", authenticationService.getActiveUser().getUsername());

    }

    @Test
    public void logOutTest() {
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        authenticationService.logIn(user);

        Assertions.assertEquals("admin", authenticationService.getActiveUser().getUsername());

        authenticationService.logOut(user);

        Assertions.assertNull(authenticationService.getActiveUser());
    }

    @Test
    public void isUserValidTest(){
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        userService.addUser(toAdd);

        User user = userService.getUserById(1);

        Assertions.assertTrue(authenticationService.isUserValid(user));
        Assertions.assertFalse(authenticationService.isUserValid(new User(-1,"user", "user")));
    }

}
