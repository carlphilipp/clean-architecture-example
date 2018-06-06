package com.slalom.example.domain.usecases;

import com.slalom.example.domain.entities.User;
import com.slalom.example.domain.ports.IdGenerator;
import com.slalom.example.domain.ports.PasswordEncoder;
import com.slalom.example.domain.ports.UserRepository;
import com.slalom.example.domain.usecases.validators.UserValidator;

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
		UserValidator.validateCreateUser(user);
		if (repository.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("User exists already");
		}
		var userToSave = User.builder()
			.id(idGenerator.generate())
			.email(user.getEmail())
			.password(passwordEncoder.encode(user.getEmail() + user.getPassword()))
			.lastName(user.getLastName())
			.firstName(user.getFirstName())
			.build();
		return repository.create(userToSave);
	}
}
