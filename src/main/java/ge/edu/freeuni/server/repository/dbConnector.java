package ge.edu.freeuni.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class dbConnector {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_website";
    private static final String USER = "username";
    private static final String PASSWORD = "12345678";
    public static Statement statement;

    @Autowired
    public dbConnector() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = con.createStatement();
    }
}
