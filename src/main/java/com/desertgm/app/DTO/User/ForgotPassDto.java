package com.desertgm.app.DTO.User;


import jakarta.validation.constraints.NotNull;

public record ForgotPassDto(
        @NotNull
        String email
) {
}
