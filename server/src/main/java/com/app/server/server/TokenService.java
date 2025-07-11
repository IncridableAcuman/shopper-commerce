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
}
