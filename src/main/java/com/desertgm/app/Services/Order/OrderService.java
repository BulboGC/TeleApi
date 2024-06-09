package com.desertgm.app.Services.Order;

import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.Order.Order;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.OrderRepository;
import com.desertgm.app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    public void addOrder(Order order){
        orderRepository.save(order);
    }

    public Order getOrder(String id){
      Optional<Order> order =  orderRepository.findById(id);
      if(order.isPresent()){
          return order.get();
      }
        throw new RuntimeException("usuario não encontrado");
    }

    public List<Order> getListOrder(String userId, UserRole userRole) throws RuntimeException {

        switch (userRole){
            case USER ->{ return orderRepository.findByUserId(userId);}
            case ADMIN ->{ orderRepository.findAll();}
            case SUPERVISOR -> {
                List< User > users =  userRepository.findBySupervisorId(userId);
                List<String> subordinateUserIds = users.stream()
                    .map(User::getId)
                    .toList();
                return orderRepository.findByUserIdIn(subordinateUserIds);
            }

        }
            return orderRepository.findByUserId(userId);
    }

    public void deleteOrder(String id){
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders(){
       return orderRepository.findAll();
    }
}
