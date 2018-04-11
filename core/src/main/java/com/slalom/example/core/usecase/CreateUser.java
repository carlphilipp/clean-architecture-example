package com.slalom.example.core.usecase;

import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.IdGenerator;
import com.slalom.example.core.spi.PasswordEncoder;
import com.slalom.example.core.spi.UserRepository;

public class CreateUser {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final IdGenerator idGenerator;

	public CreateUser(final UserRepository repository, final PasswordEncoder passwordEncoder, final IdGenerator idGenerator) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.idGenerator = idGenerator;
	}

	public User create(final User user) {
		var userToSave = new User(
			idGenerator.generate(),
			user.getEmail(),
			passwordEncoder.encode(user.getEmail() + user.getPassword())
		);
		return repository.create(userToSave);
	}
}
