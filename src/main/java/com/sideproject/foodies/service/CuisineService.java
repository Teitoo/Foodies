package com.sideproject.foodies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sideproject.foodies.beans.Cuisine;
import com.sideproject.foodies.repository.CuisineRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CuisineService {
	@Autowired
	CuisineRepository cRepo;
	
	
	public Cuisine createCuisine(Cuisine c) {
		return cRepo.save(c);
	}
	
	public List<Cuisine> findAll() {
		return cRepo.findAll();
	}
	
	public Cuisine findById(Long id) {
		return cRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cuisine not found with id: "+ id));
	}
	
	public void deleteById(Long id) {
		Cuisine c = cRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cuisine not found with id: "+ id));
		cRepo.delete(c);
	}
}
