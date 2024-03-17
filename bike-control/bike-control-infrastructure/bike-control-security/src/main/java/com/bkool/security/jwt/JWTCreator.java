package com.bkool.security.jwt;

import com.bkool.port.auth.AuthenticationCreator;
import entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTCreator implements AuthenticationCreator {

    private final String secretKey;

    private final long accessTokenValidity;

    public JWTCreator(@Value("${jwt.secret_key}") final String secretKey, @Value("${jwt.validity}") Long validity)  {
        this.secretKey = secretKey;
        this.accessTokenValidity = validity;
    }

    @Override
    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + accessTokenValidity);
        return Jwts.builder().setClaims(claims).setExpiration(tokenValidity).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
}
