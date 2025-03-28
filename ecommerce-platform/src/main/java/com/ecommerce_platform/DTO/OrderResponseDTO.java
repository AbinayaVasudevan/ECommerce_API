package com.ecommerce_platform.DTO;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce_platform.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private UserOrderDTO user;
    private List<OrderItemDTO> items;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private OrderStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserOrderDTO getUser() {
		return user;
	}
	public void setUser(UserOrderDTO user) {
		this.user = user;
	}
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public OrderResponseDTO(Long id, UserOrderDTO user, List<OrderItemDTO> items, BigDecimal totalPrice,
			LocalDateTime orderDate, OrderStatus status) {
		this.id = id;
		this.user = user;
		this.items = items;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.status = status;
	}
    
    
}

