package com.bkool.port.auth;

public interface PasswordComparator {
    boolean matches(String password, String encodedPassword);
}
