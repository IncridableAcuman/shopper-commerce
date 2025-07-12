package com.app.server.server;

import com.app.server.dto.AuthRequest;
import com.app.server.dto.AuthResponse;
import com.app.server.dto.RegisterRequest;
import com.app.server.entity.Token;
import com.app.server.entity.User;
import com.app.server.util.CookieUtil;
import com.app.server.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
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
    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        User user=userService.createUser(request);
//        generate tokens
        String accessToken= jwtUtil.generateAccessToken(user);
        String refreshToken= jwtUtil.generateRefreshToken(user);
//        token saving  to db
        Token token=tokenService.createToken(user,refreshToken);
//        add to cookie
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
    public AuthResponse login(AuthRequest request,HttpServletResponse response){
        User user=userService.findUser(request.getEmail());
        userService.isPasswordEqual(request.getPassword(), user.getPassword());
        cookieUtil.removeToken(response);
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
}
