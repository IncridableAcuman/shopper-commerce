package com.app.server.service;

import com.app.server.entity.Token;
import com.app.server.entity.User;
import com.app.server.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;


    @Transactional
    public Token createToken(User user,String refreshToken){
        Token token=new Token();
        token.setRefreshToken(refreshToken);
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        return tokenRepository.save(token);
    }
    public void findToken(String refreshToken){
        Token token = tokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new RuntimeException("Token not found"));
        if (token.getExpiryDate().isBefore(LocalDateTime.now())){
            tokenRepository.delete(token);
            throw new IllegalArgumentException("Token is expired");
        }
    }
    public Token findTokenByUser(User user){
        return tokenRepository.findByUser(user).orElseThrow(()->new IllegalArgumentException("Token not found by user"));
    }
    @Transactional
    public void regenerateToken(User user, String refreshToken){
        tokenRepository.findByUser(user).orElseGet(()->createToken(user,refreshToken));
    }
    @Transactional
    public void deleteToken(User user){
       Token token = findTokenByUser(user);
       tokenRepository.delete(token);
    }
}
