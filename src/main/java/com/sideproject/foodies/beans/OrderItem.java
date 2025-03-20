package com.sideproject.foodies.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//用來處理一筆訂單中可能有多筆相同的料理
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@ManyToOne端不使用Cascade控制，否則可能會有不受repository控制的資料流入
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "cuisine_id", nullable = false)
	private Cuisine cuisine;
	
	private int quantity;
}
