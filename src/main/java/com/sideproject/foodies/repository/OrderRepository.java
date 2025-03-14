package com.sideproject.foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sideproject.foodies.beans.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
