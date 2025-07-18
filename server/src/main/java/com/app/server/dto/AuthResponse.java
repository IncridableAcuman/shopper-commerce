package com.app.server.dto;

import com.app.server.entity.Cart;
import com.app.server.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private Cart cart;
    private String accessToken;
    private String refreshToken;

}
