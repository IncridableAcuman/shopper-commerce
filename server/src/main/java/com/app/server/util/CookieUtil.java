package com.app.server.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    @Value("${jwt.refresh-time}")
    private long refreshTime;

//    add token to cookie
    public void addCookie(String refreshToken, HttpServletResponse response){
        ResponseCookie cookie=ResponseCookie.from("refreshToken",refreshToken)
                .httpOnly(true)
                .maxAge(refreshTime)
                .secure(false)
                .path("/")
                .sameSite("None")
                .build();
        response.addHeader("Set-Cookie",cookie.toString());
    }
//   remove token from cookie
    public void removeToken(HttpServletResponse response){
        ResponseCookie cookie=ResponseCookie.from("refreshToken","")
                .httpOnly(true)
                .maxAge(refreshTime)
                .secure(false)
                .path("/")
                .sameSite("None")
                .build();
        response.addHeader("Set-Cookie",cookie.toString());
    }
}
