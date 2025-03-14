package com.sideproject.foodies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {
	@Autowired
	OrderRepository oRepo;

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
}
