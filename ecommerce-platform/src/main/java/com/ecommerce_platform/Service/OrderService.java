//package com.ecommerce_platform.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ecommerce_platform.Entity.Cart;
//import com.ecommerce_platform.Entity.CartItem;
//import com.ecommerce_platform.Entity.Order;
//import com.ecommerce_platform.Entity.OrderItem;
//import com.ecommerce_platform.Enum.OrderStatus;
//import com.ecommerce_platform.Repository.CartRepository;
//import com.ecommerce_platform.Repository.OrderRepository;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class OrderService {
//    @Autowired private OrderRepository orderRepo;
//    @Autowired private CartRepository cartRepo;
//
//    @Transactional
//    public Order placeOrder(Long userId) {
//        Cart cart = cartRepo.findByUserId(userId);
//        if (cart == null || cart.getItems().isEmpty()) {
//            throw new RuntimeException("Cart is empty");
//        }
//
//        Order order = new Order();
//        order.setUser(cart.getUser());
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        for (CartItem cartItem : cart.getItems()) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(cartItem.getProduct());
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setOrder(order); 
//            orderItems.add(orderItem);
//        }
//        order.setItems(orderItems);
//
//        // Calculate total price
//        BigDecimal total = orderItems.stream()
//            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
//            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        order.setTotalPrice(total);
//        order.setStatus(OrderStatus.PENDING);
//        order.setOrderDate(LocalDateTime.now());
//
//        // Save order (JPA will cascade save orderItems if mapped properly)
//        orderRepo.save(order);
//
//        // Clear the cart after placing an order
//        cart.getItems().clear();
//        cartRepo.save(cart);
//
//        return order;
//    }
//
//
//    public List<Order> getUserOrders(Long userId) {
//        return orderRepo.findByUserId(userId);
//    }
//}
//
package com.ecommerce_platform.Service;

import com.ecommerce_platform.DTO.*;
import com.ecommerce_platform.Entity.*;
import com.ecommerce_platform.Enum.OrderStatus;
import com.ecommerce_platform.Repository.CartRepository;
import com.ecommerce_platform.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepo;
    @Autowired private CartRepository cartRepo;

    @Transactional
    public OrderResponseDTO placeOrder(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cart.getUser());

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);

        // Calculate total price
        BigDecimal total = orderItems.stream()
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(total);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        // Save order (JPA will cascade save orderItems if mapped properly)
        orderRepo.save(order);

        // Clear the cart after placing an order
        cart.getItems().clear();
        cartRepo.save(cart);

        // Convert Order to OrderResponseDTO
        return convertToDTO(order);
    }

    public List<OrderResponseDTO> getUserOrders(Long userId) {
        return orderRepo.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderResponseDTO convertToDTO(Order order) {
        UserOrderDTO userDTO = new UserOrderDTO(order.getUser().getId(), order.getUser().getName());
        List<OrderItemDTO> itemsDTO = order.getItems().stream().map(orderItem -> 
            new OrderItemDTO(orderItem.getId(),
                new ProductDTO(orderItem.getProduct().getId(),
                               orderItem.getProduct().getName(),
                               orderItem.getProduct().getPrice()),
                orderItem.getQuantity())
        ).collect(Collectors.toList());

        return new OrderResponseDTO(
            order.getId(), userDTO, itemsDTO, 
            order.getTotalPrice(), order.getOrderDate(), order.getStatus()
        );
    }
}
