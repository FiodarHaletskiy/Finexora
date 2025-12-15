package com.fgal.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    public String getUsername;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
