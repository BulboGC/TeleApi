package com.desertgm.app.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotNull
        String password,
        @Email
        @NotNull
        String email,
        @NotNull
        String username,

        String supervisorId,

        String name,
        int role

) {
}
