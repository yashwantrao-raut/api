package com.api.auth;

import com.api.dao.UserDao;
import com.api.domain.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials,UserPrincipal> {
    private UserDao userDao;
    public BasicAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Optional<User> user = userDao.find(credentials.getUsername(), credentials.getPassword());
        if(user.isPresent())
        {
            return Optional.of (new UserPrincipal(user.get().getName(),user.get().getId()));
        }
        else {
            return Optional.empty();
        }
    }
}
