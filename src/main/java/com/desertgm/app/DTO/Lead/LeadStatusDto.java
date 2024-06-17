package com.desertgm.app.DTO.Lead;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record LeadStatusDto(
        @NotBlank
        int status,

        Date datetocall
) {
}
