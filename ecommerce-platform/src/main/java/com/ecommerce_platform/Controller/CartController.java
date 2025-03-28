package com.ecommerce_platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_platform.DTO.CartDTO;
import com.ecommerce_platform.Entity.Cart;
import com.ecommerce_platform.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartDTO> removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
}


