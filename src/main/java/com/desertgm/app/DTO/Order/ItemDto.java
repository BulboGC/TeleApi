package com.desertgm.app.DTO.Order;

import java.time.LocalDateTime;
import java.time.Year;

public record ItemDto(
        String brand,
        String model,
        String color,
        String year,
        String version,

        LocalDateTime dueDate,
        Long amount,

        String payment,
        String purchaseReason,

        String maxPayment,

        String tradeInValue,

        String orderType
) {
}
