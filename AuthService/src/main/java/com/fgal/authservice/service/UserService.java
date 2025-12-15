package com.fgal.authservice.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('USER')")
public class UserService {

    public void register(String username, String password) {}
    public void login(String username, String password) {}
    public void logout() {}
    public void changePassword(String username, String password) {}
    public void changeUsername(String username) {}
    public void changeEmail(String username) {}
    public void deleteUser(String username) {}
}
