package com.sideproject.foodies.service;

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
		if(uRepo.findByUsername(username).isPresent()) {
			throw new NameReplicateException("Name " + username + "already exist");
		}
		else {
			User u = new User();
			u.setUsername(username);
			u.setPassword(pwEncoder.encode(rawPw));
			u.setEmail(email);
			u.setRole(role);
		}
		UserDTO u = new UserDTO(username, email, role);
		return u;
	}

	public List<User> findAll() {
		return uRepo.findAll();
	}

	public User findById(Long id) {
		return uRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	public User updateUser(Long id, User u) {
		User oldUser = uRepo.findById(id).map(existingUser -> {
			existingUser.setUsername(u.getUsername());
			existingUser.setPassword(u.getPassword());
			existingUser.setAddress(u.getAddress());
			existingUser.setEmail(u.getEmail());
			existingUser.setPhoneNumber(u.getPhoneNumber());
			return existingUser;
		}).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		// 清除User和Order的原有關聯後新增
		oldUser.getOrders().forEach(order -> order.setUser(null));
		for (Order o : u.getOrders()) {
			o.setUser(u);
			u.getOrders().add(o);
		}
		return uRepo.save(u);
	}

	public void deleteById(Long id) {
		User u = uRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
		uRepo.delete(u);
	}

}
