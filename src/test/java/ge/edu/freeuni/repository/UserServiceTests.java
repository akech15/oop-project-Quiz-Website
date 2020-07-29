package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

@SpringBootTest
public class UserServiceTests {

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
    public void addUserTest() {
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        Assertions.assertTrue(userService.addUser(toAdd));
    }

    @Test
    public void getUserByIdTest() {
        {
            User toAdd = new User();
            toAdd.setUsername("admin");
            toAdd.setPassword("admin");
            userService.addUser(toAdd);
        }

        Assertions.assertEquals("admin", userService.getUserById(1).getUsername());
    }

    @Test
    public void getUsersByUsernameFragment(){
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");

        User toAdd1 = new User();
        toAdd1.setUsername("admin1");
        toAdd1.setPassword("admin1");

        userService.addUser(toAdd);
        userService.addUser(toAdd1);

        Assertions.assertEquals(2, userService.getUsersByUsernameFragment("ad").size());
        Assertions.assertEquals(0, userService.getUsersByUsernameFragment("us").size());
    }
}
