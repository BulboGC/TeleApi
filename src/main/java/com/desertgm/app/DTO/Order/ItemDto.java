package com.desertgm.app.DTO.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ItemDto(
        @NotNull
        @NotBlank
        String brand,
        @NotNull
        @NotBlank
        String model,
        @NotNull
        @NotBlank
        String color,
        String year,//ano
        String version,

        Date dueDate,//data
        Long amount,//numero

        String payment,
        String purchaseReason,

        String maxPayment,

        String tradeInValue,

        String orderType
) {
}
