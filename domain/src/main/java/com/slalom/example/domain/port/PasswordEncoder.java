package com.slalom.example.domain.port;

@FunctionalInterface
public interface PasswordEncoder {
	String encode(String str);
}
