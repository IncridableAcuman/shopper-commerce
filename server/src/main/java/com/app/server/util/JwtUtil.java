package com.app.server.util;

import com.app.server.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-time}")
    private long accessTime;

    @Value("${jwt.refresh-time}")
    private long refreshTime;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

//    generate accessToken
    public String generateAccessToken(User user){
        return Jwts
                .builder()
                .subject(user.getEmail())
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .claim("role",user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+accessTime))
                .signWith(getSigningKey())
                .compact();
    }
//    generate refreshToken
    public String generateRefreshToken(User user) {
        return Jwts
                .builder()
                .subject(user.getEmail())
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .claim("role",user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+refreshTime))
                .signWith(getSigningKey())
                .compact();
    }
//    validate token
    public boolean validateToken(String token){
        try {
             Jwts.parser().build().parseSignedClaims(token);
             return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
//    is token expired
    public boolean isTokenExpired(String token){
        return Jwts
                .parser()
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
//    extract subject from token
    public String extractSubject(String token){
        return Jwts
                .parser()
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
//    extract expiration from token
    public Date extractExpiration(String token){
        return Jwts
                .parser()
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
