package com.sideproject.foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sideproject.foodies.beans.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
