package com.app.server.dto;

import com.app.server.enums.Role;
import lombok.Data;

@Data
public class AuthData {
    private Long id;
    private String username;
    private Role role;

    public AuthData(Long id, String username, Role role) {
        this.id=id;
        this.username=username;
        this.role=role;
    }
}
