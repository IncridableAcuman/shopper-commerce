package com.app.server.server;

import com.app.server.dto.AuthRequest;
import com.app.server.dto.AuthResponse;
import com.app.server.dto.RegisterRequest;
import com.app.server.entity.Token;
import com.app.server.entity.User;
import com.app.server.util.CookieUtil;
import com.app.server.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;

//    register
    @Transactional
    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        User user=userService.createUser(request);
        String accessToken= jwtUtil.generateAccessToken(user);
        String refreshToken= jwtUtil.generateRefreshToken(user);
        Token token=tokenService.createToken(user,refreshToken);
        cookieUtil.addCookie(token.getRefreshToken(),response);
        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                accessToken,
                refreshToken
        );
    }
//    login
    @Transactional
    public AuthResponse login(AuthRequest request,HttpServletResponse response){
        User user=userService.findUser(request.getEmail());
        userService.validatePassword(request.getPassword(), user.getPassword());
        tokenService.deleteToken(user);
        String newAccessToken= jwtUtil.generateAccessToken(user);
        String newRefreshToken= jwtUtil.generateRefreshToken(user);
        tokenService.regenerateToken(user,newRefreshToken);
        cookieUtil.addCookie(newRefreshToken,response);
        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                newAccessToken,
                newRefreshToken
        );
    }
//    refresh
    @Transactional
    public AuthResponse refresh(String refreshToken,HttpServletResponse response){
        if(refreshToken==null){
            throw new IllegalArgumentException("Refresh token is empty or invalid");
        }
        jwtUtil.validateToken(refreshToken);
        tokenService.findToken(refreshToken);
        String email= jwtUtil.extractSubject(refreshToken);
        if(email==null){
            throw new IllegalArgumentException("Invalid token");
        }
        User user=userService.findUser(email);
        tokenService.deleteToken(user);
        String accessToken= jwtUtil.generateAccessToken(user);
        String newRefreshToken= jwtUtil.generateRefreshToken(user);
        tokenService.regenerateToken(user,newRefreshToken);
        cookieUtil.addCookie(newRefreshToken,response);
        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                accessToken,
                newRefreshToken
        );
    }
//    logout
    public void logout(String refreshToken,HttpServletResponse response){
        if(refreshToken==null){
            throw new RuntimeException("Refresh token is empty or invalid");
        }
        jwtUtil.validateToken(refreshToken);
        tokenService.findToken(refreshToken);
        String email= jwtUtil.extractSubject(refreshToken);
        if(email==null){
            throw new IllegalArgumentException("Invalid token");
        }
        User user=userService.findUser(email);
        tokenService.findToken(refreshToken);
        tokenService.deleteToken(user);
        cookieUtil.removeToken(response);
    }
}
