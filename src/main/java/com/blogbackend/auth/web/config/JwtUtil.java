package com.blogbackend.auth.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String ACCESS_SECRET = "bl0g-4pp";
    private static final String REFRESH_SECRET = "bl0g-4pp_refresh";
    private final Algorithm ACCESS_ALGORITHM;
    private final Algorithm REFRESH_ALGORITHM;
    public JwtUtil(){
        this.ACCESS_ALGORITHM = Algorithm.HMAC256(ACCESS_SECRET);
        this.REFRESH_ALGORITHM = Algorithm.HMAC256(REFRESH_SECRET);
    }

    public String createAccessToken(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("blog-app")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
                .sign(ACCESS_ALGORITHM);
    }

    public boolean isAccessTokenValid(String jwt) {
        try {
            JWT.require(ACCESS_ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsernameFromAccessToken(String jwt){
        return JWT.require(ACCESS_ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }

}
