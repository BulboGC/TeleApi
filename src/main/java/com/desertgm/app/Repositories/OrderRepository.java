package com.desertgm.app.Repositories;

import com.desertgm.app.Models.Order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
     List<Order> findByUserId(String userId);
}
