package com.api.auth;

import java.security.Principal;

public class UserPrincipal implements Principal{

    private String name;
    private Long  userId;

    public UserPrincipal(String name,Long userId) {
        this.name = name;
        this.userId=userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

}
