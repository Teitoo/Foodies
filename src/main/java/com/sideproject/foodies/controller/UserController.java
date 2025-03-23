package com.sideproject.foodies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.foodies.beans.User;
import com.sideproject.foodies.beans.UserDTO;
import com.sideproject.foodies.exception.NameReplicateException;
import com.sideproject.foodies.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

    // Admin
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	//填寫完整表格
	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody User u) throws NameReplicateException {
		return userService.registerUser(u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteById(id);
	}
}
