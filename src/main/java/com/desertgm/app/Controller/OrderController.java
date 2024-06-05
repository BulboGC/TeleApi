package com.desertgm.app.Controller;

import com.desertgm.app.DTO.Order.OrderDto;
import com.desertgm.app.Enums.Order.OrderStatus;
import com.desertgm.app.Models.Order.Order;
import com.desertgm.app.Services.Order.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id
    ){
       var order =  orderService.getOrder(id);
       if(order != null){
           return ResponseEntity.ok().body(order);
       }
       return ResponseEntity.badRequest().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto, @RequestAttribute("userId") String userId){
        Order order = new Order(userId,OrderStatus.valueOf(orderDto.status()),orderDto.orderItem());
        orderService.addOrder(order);
        return ResponseEntity.ok().body("Pedido Salvo com sucesso");
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getOrderList(@RequestAttribute("userId") String userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin){
            orderService.getAllOrders();
        }else {

        }
        var orders = orderService.getListOrder(userId);
        return ResponseEntity.ok().body(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Pedido deletado com sucesso");
    }

}
