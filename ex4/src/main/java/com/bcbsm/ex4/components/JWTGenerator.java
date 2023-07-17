package com.bcbsm.ex4.components;

import java.util.Date;

import com.bcbsm.ex4.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JWTGenerator {

    @Value("${bcbsm.jwt.expr}")
    long jwtExpirationTime;

    final UserRepository userRepository;

    public JWTGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationTime);
        Claims claims = Jwts.claims().setSubject(username);
        String role = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found")).getRole();
        claims.put("role", role);


        String token = Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        log.info("New token :{}", token);
        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect", ex.fillInStackTrace());
        }
    }

}