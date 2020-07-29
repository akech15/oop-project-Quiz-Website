package ge.edu.freeuni.server.services.authentication;


import ge.edu.freeuni.api.model.user.User;


public interface AuthenticationService {

    boolean isUserValid(User user);

    User getActiveUser();

    boolean logIn(User user);

    void logOut(User user);

}
