package com.fgal.authservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String password;
    private boolean isActive;
    private Role role;
}
