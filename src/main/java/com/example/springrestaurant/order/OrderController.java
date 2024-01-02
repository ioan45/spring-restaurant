package com.example.springrestaurant.order;

import com.example.springrestaurant.order.dto.AddOrderRequest;
import com.example.springrestaurant.order.dto.MenuItemEntry;
import com.example.springrestaurant.order.dto.OrderDtoMapper;
import com.example.springrestaurant.order.dto.RemoveOrderRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/add")
    public ResponseEntity<String> addOrder(
            @RequestBody @Valid AddOrderRequest req
    ) {
        OrderEntity order = OrderDtoMapper.addRequest(req);
        orderService.addOrder(order, req.getIdClient(), req.getMenuItems());
        return ResponseEntity.ok().body("Order added successfully!");
    }

    @PostMapping("/order/remove")
    public ResponseEntity<String> removeOrder(
            @RequestBody @Valid RemoveOrderRequest req
    ) {
        orderService.removeOrder(req.getIdOrder());
        return ResponseEntity.ok().body("Order removed successfully!");
    }

    @GetMapping("/order/get/all")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/order/get/by/client")
    public ResponseEntity<List<OrderEntity>> getOrdersByClient(
            @RequestParam int id
    ) {
        return ResponseEntity.ok().body(orderService.getOrdersByClient(id));
    }

    @GetMapping("/ordered-items/get/by/order-id")
    public ResponseEntity<List<MenuItemEntry>> getOrderedItemsByOrderId(
            @RequestParam int id
    ) {
        return ResponseEntity.ok().body(orderService.getOrderedItemsByOrderId(id));
    }
}
