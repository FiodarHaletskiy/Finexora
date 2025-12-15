package com.fgal.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeEmailRequest {
    @Email
    @NotBlank
    private String email;
    @Email
    @NotBlank
    private String newEmail;
    @NotBlank
    @Size(min=8, max=48)
    private String password;
}
