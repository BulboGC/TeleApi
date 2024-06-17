package com.desertgm.app.Services.Order;

import com.desertgm.app.DTO.Order.OrderDto;
import com.desertgm.app.Enums.Lead.LeadStatus;
import com.desertgm.app.Enums.Order.OrderStatus;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.Leads.Lead;
import com.desertgm.app.Models.Order.Item;
import com.desertgm.app.Models.Order.Order;
import com.desertgm.app.Models.User.User;
import com.desertgm.app.Repositories.LeadRepository;
import com.desertgm.app.Repositories.OrderRepository;
import com.desertgm.app.Repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LeadRepository leadRepository;

    @Transactional
    public void addOrder(Order order,String leadId){
        var lead = leadRepository.findById(leadId);
        Lead newlead = lead.get();
        /* Aterar o Status do Lead*/
        newlead.setStatus(LeadStatus.CONFIRMED.getLeadStatus());
        leadRepository.save(newlead);
        /* Salvar no banco*/
        orderRepository.save(order);
    }

    public Order getOrder(String id){
      Optional<Order> order =  orderRepository.findById(id);
      if(order.isPresent()){
          return order.get();
      }
        throw new RuntimeException("usuario não encontrado");
    }
    public List<Order> getOrdersByLeadId(String id){
        return orderRepository.findByLeadId(id);
    }


    public Order editOrder(Order order, Item item){

        order.setOrderItem(item);
        return orderRepository.save(order);
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

    public Order updateStatus(OrderStatus status,String orderId){

       Optional<Order> OpOrder =  orderRepository.findById(orderId);
       var order = OpOrder.get();
       if(order.getStatus() == OrderStatus.PAID){
           throw new RuntimeException("não é possível alterar o status de um pedido já finalizado");
       }
        if(order.getStatus() == OrderStatus.CANCELLED){
            throw new RuntimeException("não é possível alterar o status de um pedido cancelado");
        }
       order.setStatus(status);
       orderRepository.save(order);
       return order;
    }

}
