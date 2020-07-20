package ge.edu.freeuni.server.services.authentication;

import ge.edu.freeuni.server.model.user.UserEntity;

public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public boolean logIn(UserEntity user) {
        return false;
    }

    @Override
    public void logOut(UserEntity user) {

    }
}
