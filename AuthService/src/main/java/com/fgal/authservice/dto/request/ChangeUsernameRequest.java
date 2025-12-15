package com.fgal.authservice.dto.request;

import lombok.Data;

@Data
public class ChangeUsernameRequest {
    private String oldUsername;
    private String newUsername;
    private String password;
}
