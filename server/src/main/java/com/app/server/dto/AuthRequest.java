package com.app.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Email must be required!")
    @Email(message = "example@gmail.com")
    private String email;

    @NotBlank(message = "Password must be required!")
    @Size(min = 8,max = 1024,message = "Password must be least 8 characters than")
    private String password;
}
