package com.desertgm.app.Controller;

import com.desertgm.app.DTO.NewResponseDto;
import com.desertgm.app.DTO.Order.ItemDto;
import com.desertgm.app.DTO.Order.OrderDto;
import com.desertgm.app.Enums.Order.OrderStatus;
import com.desertgm.app.Enums.UserRole;
import com.desertgm.app.Models.Order.Item;
import com.desertgm.app.Models.Order.Order;
import com.desertgm.app.Services.Order.OrderService;
import com.desertgm.app.Services.User.UserService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<NewResponseDto> getOrderbylead(@PathVariable("id") String id
    ){
        var orderList =  orderService.getOrdersByLeadId(id);
        NewResponseDto responseDto = new NewResponseDto("pedidos encontrados","OK",orderList);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/{orderId}")
    public  ResponseEntity<NewResponseDto> updatePost(
            @RequestBody Item item,
            @RequestAttribute("userId") String userId,
            @PathVariable String orderId)
        {

            Order order =  orderService.getOrder(orderId);
            if(order.getStatus() == OrderStatus.PAID){
                throw new RuntimeException("Não é possivel alterar status de um pedido ja finalizado");
            }
            order.setOrderItem(item);
            Order editOrder =  orderService.editOrder(order,item);

            NewResponseDto responseDto = new NewResponseDto("Pedido encontrado","OK",editOrder);
            return ResponseEntity.ok().body(responseDto);
        }



    @PostMapping("/{leadId}")
    public ResponseEntity<String> addOrder(
            @Valid
            @RequestBody ItemDto item,
            @RequestAttribute("userId") String userId,
            @PathVariable String leadId)
    {

        Item item1 = new Item();
        BeanUtils.copyProperties(item,item1);
        var objectId = new ObjectId(userId);
        Order order = new Order(objectId,OrderStatus.PENDING, item1,leadId);
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
