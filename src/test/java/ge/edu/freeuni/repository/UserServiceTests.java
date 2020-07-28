package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserServiceImpl userService;

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
    public void addUser() {
        User toAdd = new User();
        toAdd.setUsername("admin");
        toAdd.setPassword("admin");
        Assertions.assertTrue(userService.addUser(toAdd));
    }

    @Test
    public void getUserById() {
        {
            User toAdd = new User();
            toAdd.setUsername("admin");
            toAdd.setPassword("admin");
            userService.addUser(toAdd);
        }

        Assertions.assertEquals("admin", userService.getUserById(1).getUsername());
    }
}
