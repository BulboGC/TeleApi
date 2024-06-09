package com.desertgm.app.DTO.User;

import java.time.LocalDateTime;

public record RecoveryTokenDto(
        Long id,

        String userId,

        LocalDateTime expirationDate
) {
}
