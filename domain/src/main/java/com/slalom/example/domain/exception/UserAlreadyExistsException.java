package com.slalom.example.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String email) {
		super(email);
	}
}
