package com.desertgm.app.DTO.Order;

import com.desertgm.app.Models.Order.Item;

import java.util.List;

public record OrderDto(
        String userId,
        String status,
        List<Item>orderItem
) {
}
