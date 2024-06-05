package com.desertgm.app.Enums.Order;

public enum OrderStatus {
    PENDING,
    CANCELLED,
    PAID,
    SUSPENDED;



    public static OrderStatus fromString(String status) {
        switch (status.toUpperCase()) {
            case "PENDING":
                return PENDING;
            case "CANCELLED":
                return CANCELLED;
            case "PAID":
                return PAID;
            case "SUSPENDED":
                return SUSPENDED;

            default:
                throw new IllegalArgumentException("Unknown sale status: " + status);
        }
    }
}
