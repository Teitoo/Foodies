package com.sideproject.foodies.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.foodies.beans.Cuisine;
import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.beans.User;
import com.sideproject.foodies.service.CuisineService;
import com.sideproject.foodies.service.OrderService;
import com.sideproject.foodies.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CuisineService cuisineService;
	
	@Autowired
	private OrderService orderService;


	
	//填寫完整表格
	@PostMapping("/register")
	public User registerUser(User u) {
		return userService.createUser(u);
	}
	
	@GetMapping("/menu")
	public List<Cuisine> getMenu() {
		return cuisineService.findAll();
	}
	
	@PostMapping("/{userId}/order")
	public Order placeOrder(@PathVariable Long id, @RequestBody Map<Long, Integer> cuisineQuantity) {
		return orderService.updateOrder(id, cuisineQuantity);
	}
}
