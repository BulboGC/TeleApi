package com.desertgm.app.Controller;

import com.desertgm.app.DTO.Order.OrderDto;
import com.desertgm.app.Enums.Order.OrderStatus;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.Order.Order;
import com.desertgm.app.Services.Order.OrderService;
import com.desertgm.app.Services.User.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id
    ){
       var order =  orderService.getOrder(id);
       if(order != null){
           return ResponseEntity.ok().body(order);
       }
       return ResponseEntity.badRequest().build();
    }

    @GetMapping("/lead/{id}")
    public ResponseEntity<List<Order>> getOrderbylead(@PathVariable("id") String id
    ){
        var orderList =  orderService.getOrdersByLeadId(id);
        return ResponseEntity.ok().body(orderList);
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto, @RequestAttribute("userId") String userId,@PathVariable String leadId){
        var objectId = new ObjectId(userId);
        Order order = new Order(objectId,OrderStatus.valueOf(orderDto.status()), orderDto.orderItems(),leadId);
        orderService.addOrder(order,leadId);
        return ResponseEntity.ok().body("Pedido Salvo com sucesso");
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getOrderList(@RequestAttribute("userId") String userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUserById(userId);
        var orders = orderService.getListOrder(userId, UserRole.fromValue(user.getRole()));
        return ResponseEntity.ok().body(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Pedido deletado com sucesso");
    }

}
