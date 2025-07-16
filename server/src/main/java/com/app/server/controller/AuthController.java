package com.app.server.controller;

import com.app.server.dto.*;
import com.app.server.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
//    register
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request, HttpServletResponse response){
        return ResponseEntity.ok(authService.register(request,response));
    }
//    login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request,HttpServletResponse response){
        return ResponseEntity.ok(authService.login(request,response));
    }
//    refresh
    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestHeader("Authorization") String authorization,HttpServletResponse response){
        if(!authorization.startsWith("Bearer ")){
            throw new RuntimeException("Invalid token format");
        }
        String refreshToken=authorization.substring(7);
        return ResponseEntity.ok(authService.refresh(refreshToken,response));
    }
//    logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorization,HttpServletResponse response){
        if(!authorization.startsWith("Bearer ")){
            throw new RuntimeException("Invalid token");
        }
        String refreshToken=authorization.substring(7);
        authService.logout(refreshToken,response);
        return ResponseEntity.ok("Logged out");
    }
//    forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request){
        return ResponseEntity.ok(authService.forgotPassword(request));
    }
    //    reset password
    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPassword resetPassword){
        return ResponseEntity.ok(authService.resetPassword(resetPassword));
    }
    @GetMapping("/user-data")
    public ResponseEntity<AuthData> userData(){
        return ResponseEntity.ok(authService.userData());
    }
}
