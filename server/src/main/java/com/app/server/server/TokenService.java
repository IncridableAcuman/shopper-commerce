package com.app.server.server;

import com.app.server.entity.Token;
import com.app.server.entity.User;
import com.app.server.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token createToken(User user,String refreshToken){
        Token token=new Token();
        token.setRefreshToken(refreshToken);
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        return tokenRepository.save(token);
    }
    public Token findToken(String refreshToken){
        return tokenRepository.findByRefreshToken(refreshToken).orElseThrow(()->new RuntimeException("Token not found"));
    }
    public Token findTokenExpiryDate(LocalDateTime expiryDate){
        return tokenRepository.findByExpiryDate(expiryDate).orElseThrow(()->new RuntimeException("Token not found by expiry date"));
    }
    public Token findTokenByUser(User user){
        return tokenRepository.findByUser(user).orElseThrow(()->new RuntimeException("Token not found by user"));
    }
    public Token regenerateToken(User user,String refreshToken){
        return tokenRepository.findByUser(user).orElseGet(()->createToken(user,refreshToken));
    }
}
