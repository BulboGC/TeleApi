package com.desertgm.app.DTO.User;

import com.desertgm.app.Models.Email.Email;
import jakarta.validation.constraints.NotNull;

public record ForgotPassDto(
        @NotNull
        String email
) {
}
