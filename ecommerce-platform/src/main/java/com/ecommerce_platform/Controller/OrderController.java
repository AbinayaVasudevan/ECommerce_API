package com.ecommerce_platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_platform.DTO.OrderResponseDTO;
import com.ecommerce_platform.Entity.Order;
import com.ecommerce_platform.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.placeOrder(userId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderResponseDTO>> getOrderHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}

