package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.mail.MailService;
import ge.edu.freeuni.server.services.user.UserService;
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
public class MailServiceTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

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
    public void GetMailTest() {
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

        Mail mail = new Mail();
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.setContext("hello");

        mailService.sendMail(mail);
        Assertions.assertEquals("bla", mailService.searchMailsByReceiver(receiver).get(0).getReceiver().getUsername());
        Assertions.assertEquals("admin", mailService.searchMailsByReceiver(receiver).get(0).getSender().getUsername());
        Assertions.assertEquals("admin", mailService.searchMailsBySender(sender).get(0).getSender().getUsername());
        Assertions.assertEquals("bla", mailService.searchMailsBySender(sender).get(0).getReceiver().getUsername());
        Assertions.assertEquals("hello", mailService.searchMailsByReceiver(receiver).get(0).getContext());

    }

    @Test
    void removeMailTest() {
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

        Mail mail = new Mail();
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.setContext("hello");

        mailService.sendMail(mail);

        Mail mail1 = mailService.searchMailsByReceiver(receiver).get(0);
        mailService.removeMail(mail1);

        Assertions.assertEquals(0, mailService.searchMailsBySender(sender).size());
        Assertions.assertEquals(0, mailService.searchMailsByReceiver(receiver).size());
    }


}
