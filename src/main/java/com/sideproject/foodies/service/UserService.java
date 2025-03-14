package com.sideproject.foodies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sideproject.foodies.beans.User;
import com.sideproject.foodies.exception.EntityNotFoundException;
import com.sideproject.foodies.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository uRepo;
	
	public List<User> findAll() {
		return uRepo.findAll();
	}
	
	public User findById(Long id) {
		return uRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}
	
	public User createUser(User u) {
		return uRepo.save(u);
	}
	
	public void deleteById(Long id) {
		User u = uRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: "+ id));
		uRepo.delete(u);
	}
}
