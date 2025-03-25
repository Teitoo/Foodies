package com.sideproject.foodies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sideproject.foodies.beans.Order;
import com.sideproject.foodies.beans.Role;
import com.sideproject.foodies.beans.User;
import com.sideproject.foodies.beans.UserDTO;
import com.sideproject.foodies.exception.EntityNotFoundException;
import com.sideproject.foodies.exception.NameReplicateException;
import com.sideproject.foodies.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository uRepo;
	@Autowired
	PasswordEncoder pwEncoder;

	public UserDTO registerUser(String username, String rawPw, String email, Role role) throws NameReplicateException {
		User u = new User();
		if (uRepo.findByUsername(username).isPresent()) {
			throw new NameReplicateException("Name " + username + "already exist");
		} else {
			u.setUsername(username);
			u.setPassword(pwEncoder.encode(rawPw));
			u.setEmail(email);
			u.setRole(role);
		}
		UserDTO userDTO = new UserDTO(u);
		return userDTO;
	}

	public List<User> findAll() {
		return uRepo.findAll();
	}

	public User findById(Long id) {
		return uRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	public UserDTO updateUser(Long id, User u) {
		User existingUser = uRepo.findById(id).map(user -> {
			user.setUsername(u.getUsername());
			user.setPassword(pwEncoder.encode(u.getPassword()));
			user.setAddress(u.getAddress());
			user.setEmail(u.getEmail());
			user.setPhoneNumber(u.getPhoneNumber());
			return user;
		}).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		// 清除User和Order的原有關聯後新增
		List<Order> oldOrders = new ArrayList<>(existingUser.getOrders());
		oldOrders.forEach(order -> order.setUser(null));
		existingUser.getOrders().clear();

		if (u.getOrders() != null) {
			for (Order o : u.getOrders()) {
				o.setUser(existingUser);
				existingUser.getOrders().add(o);
			}
		}
		uRepo.save(existingUser);
		return new UserDTO(existingUser);
	}

	public void deleteById(Long id) {
		User u = uRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		uRepo.delete(u);
	}

}
