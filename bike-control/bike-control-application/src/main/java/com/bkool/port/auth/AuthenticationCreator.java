package com.bkool.port.auth;

import entity.User;

public interface AuthenticationCreator {

    String createToken(User user);
}
