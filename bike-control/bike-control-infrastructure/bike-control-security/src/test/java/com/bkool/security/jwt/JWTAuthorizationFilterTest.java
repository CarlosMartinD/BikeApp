package com.bkool.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JWTAuthorizationFilterTest {

    @Mock
    private JWTResolver jwtResolver;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JWTAuthorizationFilter jwtAuthorizationFilter;


    @Test
    void shouldPassValidToken() throws ServletException, IOException {
        String token = "valid_token";
        Claims claims = mock(Claims.class);
        when(jwtResolver.resolveToken(any())).thenReturn(token);
        when(jwtResolver.resolveClaims(request)).thenReturn(claims);
        when(jwtResolver.validateClaims(claims)).thenReturn(true);

        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    void shouldNotPassInvalidToken() throws ServletException, IOException {
        String token = "invalid_token";
        Claims claims = mock(Claims.class);
        when(jwtResolver.resolveToken(request)).thenReturn(token);
        when(jwtResolver.resolveClaims(request)).thenReturn(claims);
        when(jwtResolver.validateClaims(claims)).thenReturn(false);

        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldNotPassNoToke() throws ServletException, IOException {
        when(jwtResolver.resolveToken(request)).thenReturn(null);

        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        verify(jwtResolver).resolveToken(request);
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

}
