package com.slalom.example.db;

public class DuplicateKeyException extends RuntimeException {
	public DuplicateKeyException() {
		super("Duplicate key");
	}
}
