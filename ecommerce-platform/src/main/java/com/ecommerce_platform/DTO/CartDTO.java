package com.ecommerce_platform.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
    private Long id;
    private UserCartDTO user;
    private List<CartItemDTO> items;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserCartDTO getUser() {
		return user;
	}
	public void setUser(UserCartDTO user) {
		this.user = user;
	}
	public List<CartItemDTO> getItems() {
		return items;
	}
	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}
    
}

