package com.app.server.server;

import com.app.server.dto.*;
import com.app.server.entity.User;
import com.app.server.util.CookieUtil;
import com.app.server.util.JwtUtil;
import com.app.server.util.MailUtil;
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
    private final MailUtil mailUtil;

    @Transactional
    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        User user=userService.createUser(request);
        String accessToken= jwtUtil.generateAccessToken(user);
        String refreshToken= jwtUtil.generateRefreshToken(user);
        tokenService.createToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return userService.responseUser(user,accessToken,refreshToken);
    }
    @Transactional
    public AuthResponse login(AuthRequest request,HttpServletResponse response){
        User user=userService.findUser(request.getEmail());
        userService.validatePassword(request.getPassword(), user.getPassword());
        tokenService.deleteToken(user);
        String newAccessToken= jwtUtil.generateAccessToken(user);
        String newRefreshToken= jwtUtil.generateRefreshToken(user);
        tokenService.regenerateToken(user,newRefreshToken);
        cookieUtil.addCookie(newRefreshToken,response);
        return userService.responseUser(user,newAccessToken,newRefreshToken);
    }
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
        return userService.responseUser(user,accessToken,refreshToken);
    }
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
    @Transactional
    public String forgotPassword(ForgotPasswordRequest request){
        User user=userService.findUser(request.getEmail());
        String token= jwtUtil.generateAccessToken(user);
        mailUtil.sendMail(user.getEmail(), "<h1>Forgot Password</h1>","http://localhost:5173/reset-password?token="+token);
        return "Reset password link sent to email";
    }
    @Transactional
    public String resetPassword(ResetPassword resetPassword){
        if(resetPassword.getToken()==null){
            throw new IllegalArgumentException("Invalid token");
        }
        String email= jwtUtil.extractSubject(resetPassword.getToken());
        if(email==null){
            throw new IllegalArgumentException("Email is empty!");
        }
        User user=userService.findUser(email);
        userService.updatePassword(user,resetPassword.getPassword());
        return "Password updated successFully";
    }
}
