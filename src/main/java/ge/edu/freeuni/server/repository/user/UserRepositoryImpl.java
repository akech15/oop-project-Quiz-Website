package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.dbConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private int idCounter = 1;

    @Override
    public boolean addUserToDB(User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        if(!isValidUserName(username))
            return false;
        System.out.println("moida");
        idCounter++;
        String toExecute = "insert into user (id, username, password) values (" + idCounter + ","
                + "\"" + username + "\" , \"" + password + "\");";

        dbConnector.statement.executeUpdate(toExecute);

        return true;
    }

    private boolean isValidUserName(String username) throws SQLException {
        String search = "select u.username from user u where u.username = \"" + username + "\";";

        ResultSet rs = dbConnector.statement.executeQuery(search);
        List<String> users = new ArrayList<String>();
        while(rs.next()){
            users.add(rs.getString(1));
        }

        return users.size() == 0;
    }

    @Override
    public boolean isValid(User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        String search = "select u.username from user u where u.username = \"" + username + "\""
                + " and u.password = \"" + password + "\";";

        ResultSet rs = dbConnector.statement.executeQuery(search);
        List<String> users = new ArrayList<String>();
        while(rs.next()){
            users.add(rs.getString(1));
        }

        return users.size() == 1;
    }
}
