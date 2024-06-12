package com.desertgm.app.DTO.Order;

import com.desertgm.app.Models.Order.Item;
import org.bson.types.ObjectId;

import java.util.List;

public record OrderDto(
        String leadId,
        ObjectId userId,
        String status,
        List<Item>orderItems
) {
}
