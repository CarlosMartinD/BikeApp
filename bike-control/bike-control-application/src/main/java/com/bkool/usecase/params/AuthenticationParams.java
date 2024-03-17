package com.bkool.usecase.params;

import entity.User;

public class AuthenticationParams {

    private final User user;

    public AuthenticationParams(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
