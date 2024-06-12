package com.desertgm.app.DTO;

public record NewResponseDto(
    String msg,
    String status,
    Object body
) {
}
