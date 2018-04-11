package com.slalom.example.core.usecase;

import com.slalom.example.core.entity.Role;
import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindUser {

	private final UserRepository repository;

	public FindUser(final UserRepository repository) {
		this.repository = repository;
	}

	public Optional<User> findById(final String id) {
		return repository.findById(id);
	}

	public Optional<User> findByEmail(final String email) {
		return repository.findByEmail(email);
	}

	public List<User> findAllUsers() {
		return repository
			.findAllUsers().stream()
			.filter(user -> user.getRole() == Role.USER)
			.collect(Collectors.toList());
	}
}
