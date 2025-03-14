package com.sideproject.foodies.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private String message;

	public EntityNotFoundException(String message) {
		this.message = message;
		System.out.println(this.message);
	}
	
}
