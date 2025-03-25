package com.sideproject.foodies.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserDTO {
	@Getter
	private String username;
	@Getter
	private String email;
	@Getter
	private String phoneNumber;
	@Getter
	private String address;
	@Getter
	private Role role;
	@Getter
	private List<Order> orders;
	
	public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.role = user.getRole();
        this.orders = user.getOrders();
    }
}
