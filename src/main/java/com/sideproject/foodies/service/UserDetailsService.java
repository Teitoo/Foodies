package com.sideproject.foodies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sideproject.foodies.repository.UserRepository;
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	@Autowired
	UserRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return uRepo.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("使用者不存在: " + username));
	}

}
