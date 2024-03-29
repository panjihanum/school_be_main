/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.school.main.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${secret.key}")
    private String secret;

    public String getTokenFrom(String bearerToken) {
        final String bearer = "Bearer ";
        if (bearerToken == null || !bearerToken.startsWith(bearer)) {
            throw new JWTVerificationException("Invalid Authorization Header");
        }
        String token = bearerToken.substring(bearer.length());
        return token;
    }

    public String getSubjectFrom(String token) {
        Algorithm algorithm = Algorithm.HMAC384(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC384(secret.getBytes());
        Instant expiration = generateExpirationTimeIn(6000);
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(expiration)
                .withIssuer("SCHOOL-API")
                .withClaim("roles", user.getRole())
                .sign(algorithm);
        return token;
    }

    private Instant generateExpirationTimeIn(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant();
    }
}
