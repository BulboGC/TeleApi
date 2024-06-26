package com.desertgm.app.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDto(
        @NotBlank String ownerRef,
        @NotBlank @Email String emailFrom,
        @NotBlank @Email String emailTo,
        @NotBlank String subject,
        @NotBlank String text
) {
}
