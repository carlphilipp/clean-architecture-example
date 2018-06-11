package com.slalom.example.domain.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(final String email) {
		super(email);
	}
}
