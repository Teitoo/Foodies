package com.sideproject.foodies.beans;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Cuisines")
public class Cuisine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuisineId;
	private String name;
	private int price;
	
	@OneToMany(mappedBy = "cuisine", cascade = CascadeType.PERSIST)
	//不使用orphanRemoval，考慮就算菜品刪除後訂單品項也應該保留
	private List<OrderItem> orderItems = new ArrayList<>();
}
