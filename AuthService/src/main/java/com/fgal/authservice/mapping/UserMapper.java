package com.fgal.authservice.mapping;

import com.fgal.authservice.dto.request.ChangeEmailRequest;
import com.fgal.authservice.dto.request.ChangeUsernameRequest;
import com.fgal.authservice.dto.request.RegisterRequest;
import com.fgal.authservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface UserMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "password", source = "encodedPassword")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "role", constant = "USER")
    User toEntity(RegisterRequest request, String encodedPassword);

    @Mapping(target = "username", source = "newUsername")
    void updateUsername(
            @MappingTarget User user,
            ChangeUsernameRequest request
    );

    @Mapping(target = "email", source = "newEmail")
    void updateEmail(
            @MappingTarget User user,
            ChangeEmailRequest request
    );
}
