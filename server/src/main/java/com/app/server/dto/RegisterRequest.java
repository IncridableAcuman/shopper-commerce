package com.app.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username must be required!")
    @Size(min = 3,max = 50,message = "Username must between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email must be required!")
    @Email(message = "example@gmail.com")
    private String email;

    @NotBlank(message = "Password must be required!")
    @Size(min = 8,max = 1024,message = "Password must be least 8 characters than")
    private String password;
}
