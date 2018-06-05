package com.slalom.example.core.exception;

// todo : :+1: d'avoir des exceptions qui te sont propres
public class NotAllowedException extends RuntimeException {
	public NotAllowedException(final String message) {
		super(message);
	}
}
