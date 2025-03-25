package com.sideproject.foodies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.beans.User;
import com.sideproject.foodies.beans.UserDTO;
import com.sideproject.foodies.exception.EntityNotFoundException;
import com.sideproject.foodies.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	private String username = "Jack";
	private String password = "123";
	@InjectMocks
	private UserService uService;

	@Mock
	private UserRepository uRepo;
	
	@Mock
	private PasswordEncoder pwEncoder;
	
	@Test
	void testFindByIdSuccess() {
		when(uRepo.findById(1L)).thenReturn(Optional.of(new User(username, password)));

		User result = uService.findById(1L);

		assertNotNull(result);
		assertEquals("Jack", result.getUsername());
	}

	@Test
	void testFindByIdFail() {
		when(uRepo.findById(1L)).thenReturn(Optional.empty());

		EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> {
			uService.findById(1L);
		});
		assertEquals("User not found with id: 1", e.getMessage());
	}

	@Test
	void testFindAll() {
		List<User> users = new ArrayList<>();
		users.add(new User(username, password));
		users.add(new User(username + "2", password));
		users.add(new User(username + "3", password));
		when(uRepo.findAll()).thenReturn(users);

		List<User> result = uService.findAll();
		assertNotNull(result);
		assertEquals(result.get(0).getUsername(), "Jack");
	}

	@Test
	void testUpdateUser() {
		String rawPassword = password + "1";
		String encodedPassword = "encodedPassword" + password;
		
		//PasswordEncoder的行為
		when(pwEncoder.encode(rawPassword)).thenReturn(encodedPassword);
		
		User existingUser = new User(username, password);
		existingUser.setUserId(1L);
		
		//模擬關聯
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setUser(existingUser);
		orders.add(order);
		existingUser.setOrders(orders);
		
		User updatedData = new User(username + "1", rawPassword);
		updatedData.setAddress("address");
		updatedData.setEmail("new@email.com");
		updatedData.setPhoneNumber("09090909");
		
		List<Order> newOrders = new ArrayList<>();
		Order newOrder = new Order();
		newOrders.add(newOrder);
		updatedData.setOrders(newOrders);
		
		when(uRepo.findById(1L)).thenReturn(Optional.of(existingUser));
		when(uRepo.save(any(User.class))).thenReturn(existingUser);
		
		UserDTO result = uService.updateUser(1L, updatedData);
		
		//verify 確認該方法有發生一次
		verify(uRepo).findById(1L);
		verify(uRepo).save(existingUser);
		verify(pwEncoder).encode(rawPassword);
		
		assertEquals(username + "1", existingUser.getUsername());
		assertEquals(encodedPassword, existingUser.getPassword());
		assertEquals("address", existingUser.getAddress());
		assertEquals("new@email.com", existingUser.getEmail());
		assertEquals("09090909", existingUser.getPhoneNumber());
		
		assertNull(order.getUser());
		assertEquals(1, existingUser.getOrders().size());
		assertEquals(existingUser, newOrder.getUser());
				
		assertNotNull(result);
		assertEquals(existingUser.getUsername(), result.getUsername());
	}
	
	@Test
	void testDeleteById() {
		User dUser = new User();
		dUser.setUserId(1L);
		
		when(uRepo.findById(1L)).thenReturn(Optional.of(dUser));
		
		uService.deleteById(1L);
		
		verify(uRepo).delete(dUser);
	}
}
