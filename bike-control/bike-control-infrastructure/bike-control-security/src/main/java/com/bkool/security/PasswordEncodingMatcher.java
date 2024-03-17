package com.bkool.security;

import com.bkool.port.auth.PasswordComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncodingMatcher implements PasswordComparator {

    private final BCryptPasswordEncoder encoder;

    public boolean matches(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
