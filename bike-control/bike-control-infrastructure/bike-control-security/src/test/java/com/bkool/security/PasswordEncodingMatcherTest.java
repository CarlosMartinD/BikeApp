package com.bkool.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingMatcherTest {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final PasswordEncodingMatcher passwordEncodingMatcher;

    public PasswordEncodingMatcherTest() {
        passwordEncodingMatcher = new PasswordEncodingMatcher(bCryptPasswordEncoder);
    }

    @Test
    void shouldEncrypt() {
        String toEncrypt = "ddddddd";
        assertTrue(passwordEncodingMatcher.matches(toEncrypt, "$2a$10$mFB7IaFEG03OhwOog9W1z.G8DRaF.DFG.XIh4HVvqWmhc55M/BUCS"));
    }
}