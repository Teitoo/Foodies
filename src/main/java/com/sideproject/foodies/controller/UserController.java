package com.sideproject.foodies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.foodies.beans.Cuisine;
import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.service.CuisineService;
import com.sideproject.foodies.service.OrderService;

@RestController
public class UserController {
	@Autowired
	private CuisineService cuisineService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/menu")
	public List<Cuisine> getMenu() {
		return cuisineService.findAll();
	}
	
	@PostMapping("/{userId}/order")
	public Order placeOrder(@PathVariable Long id, @RequestBody List<Long> cuisineIds) {
		return orderService.placeOrder(id, cuisineIds);
	}
}
