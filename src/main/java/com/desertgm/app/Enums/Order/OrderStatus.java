package com.desertgm.app.Enums.Order;

public enum OrderStatus {
    PENDING,
    CANCELLED,
    PAID,
    SUSPENDED;



    public static OrderStatus fromString(String status) {
        return switch (status.toUpperCase()) {
            case "PENDING" -> PENDING;
            case "CANCELLED" -> CANCELLED;
            case "PAID" -> PAID;
            case "SUSPENDED" -> SUSPENDED;
            default -> throw new IllegalArgumentException("Unknown sale status: " + status);
        };
    }
}
