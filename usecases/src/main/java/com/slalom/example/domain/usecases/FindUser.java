package com.slalom.example.domain.usecases;

import com.slalom.example.domain.entities.User;
import com.slalom.example.domain.ports.UserRepository;
import java.util.List;
import java.util.Optional;

public class FindUser {

	private final UserRepository repository;

	public FindUser(final UserRepository repository) {
		this.repository = repository;
	}

	public Optional<User> findById(final String id) {
		return repository.findById(id);
	}

	public List<User> findAllUsers() {
		return repository.findAllUsers();
	}
}
