package com.sideproject.foodies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.foodies.beans.Cuisine;
import com.sideproject.foodies.service.CuisineService;

@RequestMapping("/api")
@RestController
public class CuisineController {
	@Autowired
	CuisineService cuisineService;
	
	@GetMapping("/cuisines")
	public List<Cuisine> getAllCuisines() {
		return cuisineService.findAll();
	}
}
