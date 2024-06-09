package com.desertgm.app.DTO.User;

public record UpdatePasswordDto(
        String password,
        String token
) {
}
