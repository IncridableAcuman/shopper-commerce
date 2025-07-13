package com.app.server.util;

import com.app.server.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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

    private final SecretKey signingKey;

    public JwtUtil(@Value("${jwt.secret}") String secret){
        this.signingKey=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
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
                .signWith(signingKey)
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
                .signWith(signingKey)
                .compact();
    }
//    validate token
    public void validateToken(String token){
        try {
             Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token);
        } catch (SignatureException e){
            throw new JwtException("Invalid token signature");
        } catch (ExpiredJwtException e){
            throw new JwtException("Token is expired");
        } catch (MalformedJwtException e){
            throw new JwtException("Malformed token");
        } catch (Exception e){
            throw new JwtException("Token validation failed");
        }
    }
//    is token expired
    public boolean isTokenExpired(String token){
       try {
           return Jwts
                   .parser()
                   .verifyWith(signingKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getExpiration()
                   .before(new Date());
       } catch (ExpiredJwtException e){
           return true;
       }
    }
//    extract subject from token
    public String extractSubject(String token){
        try {
            return Jwts
                    .parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e){
            return null;
        }
    }
//    extract expiration from token
    public Date extractExpiration(String refreshToken){
       try {
           return Jwts
                   .parser()
                   .verifyWith(signingKey)
                   .build()
                   .parseSignedClaims(refreshToken)
                   .getPayload()
                   .getExpiration();
       } catch (JwtException e){
           return null;
       }
    }
}
