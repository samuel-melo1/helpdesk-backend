package com.samuelm.helpdesk.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.util.Date;


@Component
public class JWTUtil {

    @Value("${api.security.token.secret:12345}")
    private String secret;
    @Value("${api.security.token.expiration:3600000}")
    private Long validityInMiliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;
    public String generateToken(String email){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("helpdesk-api")
                    .withSubject(email)
                    .withExpiresAt(Instant.ofEpochSecond(System.currentTimeMillis() + validityInMiliseconds))
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null){
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(username != null && expiration != null && now.before(expiration)){
                return true;
            }
        }
        return false;
    }
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if(claims != null){
            return claims.getSubject();
        }
        return null;
    }
}
