package com.app.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPassword {
    @NotBlank(message = "Token must be required!")
    private String token;
    @NotBlank(message = "Password must be required!")
    @Size(min = 8,max = 255,message = "Password must between 8 and 255 characters!")
    private String password;
}
