package com.bkool.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JWTResolverTest {

    private String secretKey = "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3BhcmFiYXNlNjQ=";

    @Mock
    private HttpServletRequest request;

    private JWTResolver jwtResolver;

    @BeforeEach
    void setUp() {
        jwtResolver = new JWTResolver(secretKey);
    }

    @Test
    void shouldNotPassExpiredToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzEwNjkwMTQ4fQ.SJ2OezPts1NgnKSal17BH6RaaamYVUQ1rjo2njLHGc4";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        assertThrows(ExpiredJwtException.class, () -> jwtResolver.resolveClaims(request));
    }


    @Test
    void shouldResolveToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxMjExMTI4MjE4MDEzNTZ9.En8alwlyxBDsZi0D0h3t2RzyjxgURrBB1yXwxIL0lTE";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        String resolvedToken = jwtResolver.resolveToken(request);

        assertEquals(token, resolvedToken);
    }

    @Test
    void shouldReturnNullOnNoToken() {
        when(request.getHeader("Authorization")).thenReturn(null);

        String resolvedToken = jwtResolver.resolveToken(request);

        assertNull(resolvedToken);
    }
}