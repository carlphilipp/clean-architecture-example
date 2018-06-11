package com.slalom.example.domain.exception;

public class UserValidationException extends RuntimeException {
	public UserValidationException(final String message) {
		super(message);
	}
}
