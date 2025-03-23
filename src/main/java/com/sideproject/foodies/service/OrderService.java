package com.sideproject.foodies.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sideproject.foodies.beans.Cuisine;
import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.beans.OrderItem;
import com.sideproject.foodies.beans.User;
import com.sideproject.foodies.repository.CuisineRepository;
import com.sideproject.foodies.repository.OrderRepository;
import com.sideproject.foodies.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {
	@Autowired
	UserRepository uRepo;
	@Autowired
	OrderRepository oRepo;
	@Autowired
	CuisineRepository cRepo;

	public Order createOrder(Order o) {
		return oRepo.save(o);
	}

	public List<Order> findAll() {
		return oRepo.findAll();
	}

	public Order findById(Long id) {
		return oRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
	}

	public void deleteById(Long id) {
		Order dOrder = oRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
		oRepo.delete(dOrder);
	}

	public Order updateOrder(Long id, Map<Long, Integer> cuisineQuantities) {
		Order order = oRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

		Map<Long, OrderItem> currentOrderItems = order.getOrderItems().stream()
				.collect(Collectors.toMap(item -> item.getCuisine().getCuisineId(), item -> item));

		for (Map.Entry<Long, Integer> entry : cuisineQuantities.entrySet()) {
			Long cuisineId = entry.getKey();
			int quantity = entry.getValue();
			
			if (quantity <= 0) {
				if (currentOrderItems.containsKey(cuisineId))
					order.removeOrderItem(currentOrderItems.get(cuisineId));
			} else {
				Cuisine c = cRepo.findById(cuisineId)
						.orElseThrow(() -> new EntityNotFoundException("Cuisine not found with id: " + cuisineId));
				
				if (currentOrderItems.containsKey(cuisineId)) {
					currentOrderItems.get(cuisineId).setQuantity(quantity);
				} else {
					OrderItem newItem = new OrderItem();
					newItem.setCuisine(c);
					newItem.setOrder(order);
					newItem.setQuantity(quantity);
					order.addOrderItem(newItem);
				}
			}
		}
		return oRepo.save(order);
	}
	
	public List<Order> getOrderHistory(Long id) {
		User u = uRepo.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		return u.getOrders();
	}
}
