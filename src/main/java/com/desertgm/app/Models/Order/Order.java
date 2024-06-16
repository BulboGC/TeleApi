package com.desertgm.app.Models.Order;

import com.desertgm.app.Enums.Order.OrderStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document("Order")
public class Order {
    @Id
    private String id;
    @Indexed
    private String leadId;
    @Indexed
    private ObjectId userId;
    @Indexed
    private OrderStatus status;

    private Item orderItem;

    public Order(ObjectId userId, OrderStatus status,Item orderItem,String leadId) {
        this.leadId = leadId;
        this.userId = userId;
        this.status = status;
        this.orderItem = orderItem;
    }


    public ObjectId getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }




}
