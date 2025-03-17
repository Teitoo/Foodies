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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotNull
	private String account;
	@NotNull
	private String password;
	
//	user data：
	private String userName;
	private String email;
	private String phoneNumber;
	private String address;
	@OneToMany(cascade = 
		{CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE}, mappedBy = "user")
	private List<Order> orders = new ArrayList<>();
}
