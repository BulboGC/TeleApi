package com.desertgm.app.Repositories.prod;

import com.desertgm.app.Models.Order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {
     List<Order> findByUserId(String userId);
     List<Order> findByLeadId(String leadId);
     List<Order> findByUserIdIn(List<String> userIds);
}
