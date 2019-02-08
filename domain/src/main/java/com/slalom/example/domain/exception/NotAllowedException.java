package com.slalom.example.domain.exception;

public class NotAllowedException extends RuntimeException {
	public NotAllowedException(String message) {
		super(message);
	}
}
