package com.bkool.usecase;

import com.bkool.port.auth.AuthenticationCreator;
import com.bkool.port.auth.PasswordComparator;
import com.bkool.port.repository.UserRepository;
import com.bkool.port.usecase.Authentication;
import com.bkool.usecase.params.AuthenticationParams;
import entity.User;
import exception.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUseCase implements Authentication {

    private final UserRepository userRepository;

    private final AuthenticationCreator authenticationCreator;

    private final PasswordComparator passwordComparator;

    public AuthenticationUseCase(UserRepository userRepository, AuthenticationCreator authenticationCreator, PasswordComparator passwordComparator) {
        this.userRepository = userRepository;
        this.authenticationCreator = authenticationCreator;
        this.passwordComparator = passwordComparator;
    }

    @Override
    public String execute(AuthenticationParams params) {
        User userToAuthenticate = params.getUser();
        User user = userRepository.findByName(userToAuthenticate.getUsername());
        if(user == null || !passwordComparator.matches(userToAuthenticate.getPassword(), user.getPassword())) {
            throw new AuthenticationException();
        }

        return authenticationCreator.createToken(user);
    }
}
