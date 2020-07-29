package ge.edu.freeuni.repository;

import ge.edu.freeuni.api.model.friends.FriendshipStatusType;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.friendship.FriendshipService;
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
public class FriendServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

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
    public void friendshipStatusTest() {
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

        friendshipService.sendRequest(sender, receiver);
        Assertions.assertEquals(FriendshipStatusType.PENDING, friendshipService.getFriendshipStatus(sender, receiver));
        friendshipService.approveRequest(sender, receiver);
        Assertions.assertEquals(FriendshipStatusType.APPROVED, friendshipService.getFriendshipStatus(sender, receiver));
    }

    @Test
    public void removeFriendRequest() {
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

        friendshipService.sendRequest(sender, receiver);
        friendshipService.removeRequest(sender, receiver);
        Assertions.assertEquals(0, friendshipService.getAllFriendRequest(receiver).size());
    }

    @Test
    public void removeFriendTest() {
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

        friendshipService.sendRequest(sender, receiver);
        Assertions.assertTrue(friendshipService.removeFriend(sender, receiver));
    }
}
