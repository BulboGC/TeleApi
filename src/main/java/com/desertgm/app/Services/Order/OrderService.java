package com.desertgm.app.Services.Order;

import com.desertgm.app.Models.Order.Item;
import com.desertgm.app.Models.Order.Order;
import com.desertgm.app.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public Order getOrder(String id){
      Optional<Order> order =  orderRepository.findById(id);
      return order.get();
    }

    public List<Order> getListOrder(String userId) throws RuntimeException {
        try{
            return orderRepository.findByUserId(userId);
        }catch (Exception e){

            throw new RuntimeException("Erro ao buscar no banco de dados");
        }

    }

    public void deleteOrder(String id){
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders(){
       return orderRepository.findAll();
    }
}
