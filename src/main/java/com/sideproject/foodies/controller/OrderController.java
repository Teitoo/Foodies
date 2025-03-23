package com.sideproject.foodies.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.service.OrderService;

@RequestMapping("/api")
@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping("/{userId}/order")
	public List<Order> getAllOrder(@PathVariable Long id) {
		return orderService.getOrderHistory(id);
	}
	
	@PostMapping("/{userId}/order")
	public Order placeOrder(@PathVariable Long id, @RequestBody Map<Long, Integer> cuisineQuantity) {
		return orderService.updateOrder(id, cuisineQuantity);
	}
	
}
