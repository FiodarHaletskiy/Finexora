package com.fgal.authservice.repository;

import com.fgal.authservice.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotBlank String attr0);

    boolean existsByEmail(@NotBlank @Email String email);

    Optional<User> findByUsername(@NotBlank String username);
}
