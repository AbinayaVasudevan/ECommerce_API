package com.ecommerce_platform.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_platform.DTO.CartDTO;
import com.ecommerce_platform.DTO.CartItemDTO;
import com.ecommerce_platform.DTO.ProductDTO;
import com.ecommerce_platform.DTO.UserCartDTO;
import com.ecommerce_platform.Entity.Cart;
import com.ecommerce_platform.Entity.CartItem;
import com.ecommerce_platform.Entity.Product;
import com.ecommerce_platform.Entity.User;
import com.ecommerce_platform.Repository.CartItemRepository;
import com.ecommerce_platform.Repository.CartRepository;
import com.ecommerce_platform.Repository.ProductRepository;
import com.ecommerce_platform.Repository.UserRepository;

@Service
public class CartService {
    @Autowired private CartRepository cartRepo;
    @Autowired private CartItemRepository cartItemRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;

    public CartDTO addToCart(Long userId, Long productId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
            cartRepo.save(cart);
        }

        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if product already exists in cart
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cart.getItems().add(newItem);
        }

        return convertToDTO(cartRepo.save(cart));
    }

    public CartDTO removeFromCart(Long userId, Long productId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) throw new RuntimeException("Cart not found");

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            cart.getItems().remove(cartItem);
            cartItemRepo.delete(cartItem);
        }

        return convertToDTO(cartRepo.save(cart));
    }

    public CartDTO getCart(Long userId) {
        return convertToDTO(cartRepo.findByUserId(userId));
    }

    // Convert Cart Entity to DTO
    private CartDTO convertToDTO(Cart cart) {
        if (cart == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUser(new UserCartDTO(cart.getUser().getId(), cart.getUser().getName()));

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> new CartItemDTO(item.getId(),
                        new ProductDTO(item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getPrice()),
                        item.getQuantity()))
                .collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }
}


