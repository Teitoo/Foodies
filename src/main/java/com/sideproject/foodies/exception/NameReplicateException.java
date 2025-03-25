package com.sideproject.foodies.exception;

import lombok.Getter;

public class NameReplicateException extends RuntimeException {

	private static final long serialVersionUID = 2L;

	@Getter
	private String message;

	public NameReplicateException(String message) {
		this.message = message;
		System.out.println(this.message);
	}
}