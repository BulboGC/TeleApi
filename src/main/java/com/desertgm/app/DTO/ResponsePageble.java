package com.desertgm.app.DTO;

public record ResponsePageble(
        String msg,
        String status,
        int page,
        int size,
        int totalPages,
        Object body
) {
}
