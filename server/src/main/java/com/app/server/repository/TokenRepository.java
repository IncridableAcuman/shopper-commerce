package com.app.server.repository;

import com.app.server.entity.Token;
import com.app.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByUser(User user);
    Optional<Token> findByRefreshToken(String refreshToken);
    Optional<Token> findByExpiryDate(LocalDateTime expiryDate);
}
