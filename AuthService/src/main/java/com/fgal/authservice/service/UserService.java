package com.fgal.authservice.service;

import com.fgal.authservice.dto.request.LoginRequest;
import com.fgal.authservice.dto.request.RegisterRequest;
import com.fgal.authservice.dto.response.TokenResponse;
import com.fgal.authservice.entity.User;
import com.fgal.authservice.mapping.UserMapper;
import com.fgal.authservice.repository.UserRepository;
import com.fgal.authservice.security.JwtTokenService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserService {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Transactional
    public TokenResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username already exists"
            );
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new
                    ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }
        User user = userMapper.toEntity(
                request,
                passwordEncoder.encode(request.getPassword())
        );
        userRepository.save(user);
        String token = jwtTokenService.generateToken(user);
        return new TokenResponse(token);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid username or password"
                ));
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password"
            );
        }
        String token = jwtTokenService.generateToken(user);
        return new TokenResponse(token);
    }



    public void logout() {

    }

    public void changePassword(String username, String password) {

    }

    public void changeUsername(String username) {

    }

    public void changeEmail(String username) {

    }

    public void deleteUser(String username) {

    }
}
