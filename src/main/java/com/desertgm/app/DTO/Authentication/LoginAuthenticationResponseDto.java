package com.desertgm.app.DTO.Authentication;

public record LoginAuthenticationResponseDto(
        String msg,
        String status,
        String token
) {
}
