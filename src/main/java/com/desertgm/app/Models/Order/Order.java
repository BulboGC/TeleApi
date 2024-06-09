package com.desertgm.app.Models.Order;

import com.desertgm.app.Enums.Order.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Order")
public class Order {
    @Id
    private String id;
    private ObjectId userId;
    private OrderStatus status;
    private List<Item> orderItem;

    public Order(ObjectId userId, OrderStatus status,List<Item> orderItem) {
        this.userId = userId;
        this.status = status;
        this.orderItem = orderItem != null ? orderItem : new ArrayList<>();
    }

    public void addItem(Item item){
        this.orderItem.add(item);
    }

    public ObjectId getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<Item> getOrderItem() {
        return orderItem;
    }


}