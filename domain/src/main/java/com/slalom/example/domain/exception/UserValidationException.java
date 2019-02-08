package com.slalom.example.domain.exception;

public class UserValidationException extends RuntimeException {
	public UserValidationException(String message) {
		super(message);
	}
}
