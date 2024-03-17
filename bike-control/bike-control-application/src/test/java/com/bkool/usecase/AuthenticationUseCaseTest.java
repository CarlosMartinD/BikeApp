package com.bkool.usecase;

import com.bkool.port.auth.AuthenticationCreator;
import com.bkool.port.auth.PasswordComparator;
import com.bkool.port.repository.UserRepository;
import com.bkool.usecase.params.AuthenticationParams;
import entity.User;
import exception.AuthenticationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationCreator authenticationCreator;

    @Mock
    private PasswordComparator passwordComparator;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @Test
    void shouldAuthenticateUser() {
        User user = new User("name", "pass");
        User databaseUser =  new User("name", "passwithbc");
        String token = "230720323";

        when(userRepository.findByName(user.getUsername())).thenReturn(databaseUser);
        when(passwordComparator.matches(user.getPassword(), databaseUser.getPassword())).thenReturn(true);
        when(authenticationCreator.createToken(databaseUser)).thenReturn(token);

        AuthenticationParams authenticationParams = new AuthenticationParams(user);
        String returnedToken = authenticationUseCase.execute(authenticationParams);

        assertEquals(token, returnedToken);
    }

    @Test
    void shouldThrowAuthenticationExceptionOnNoUser() {
        User user = new User("name", "pass");

        when(userRepository.findByName(user.getUsername())).thenReturn(null);

        AuthenticationParams authenticationParams = new AuthenticationParams(user);
        assertThrows(AuthenticationException.class, () -> authenticationUseCase.execute(authenticationParams));
    }

    @Test
    void shouldThrowAuthenticationExceptionOnBadCredentials() {
        User user = new User("name", "pass");
        User databaseUser =  new User("name", "passwithbc");

        when(userRepository.findByName(user.getUsername())).thenReturn(databaseUser);
        when(passwordComparator.matches(user.getPassword(), databaseUser.getPassword())).thenReturn(false);

        AuthenticationParams authenticationParams = new AuthenticationParams(user);
        assertThrows(AuthenticationException.class, () -> authenticationUseCase.execute(authenticationParams));
    }

}