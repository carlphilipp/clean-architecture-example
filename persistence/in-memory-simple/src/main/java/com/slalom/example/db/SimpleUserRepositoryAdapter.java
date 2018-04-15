package com.slalom.example.db;

import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleUserRepositoryAdapter implements UserRepository {

	private final Map<String, User> inMemoryDb = Collections.emptyMap();

	@Override
	public User create(final User user) {
		return inMemoryDb.put(user.getId(), user);
	}

	@Override
	public Optional<User> findById(final String id) {
		return Optional.ofNullable(inMemoryDb.get(id));
	}

	@Override
	public Optional<User> findByEmail(final String email) {
		return inMemoryDb.values().stream()
			.filter(user -> user.getEmail().equals(email))
			.findAny();
	}

	@Override
	public List<User> findAllUsers() {
		return new ArrayList<>(inMemoryDb.values());
	}
}
