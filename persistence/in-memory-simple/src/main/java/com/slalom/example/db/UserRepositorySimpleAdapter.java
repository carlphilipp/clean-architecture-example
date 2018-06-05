package com.slalom.example.db;

import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// todo : gros :+1: pour le mode in memory en pur java (sans spring) je pense que la sur couche spring Hazel Cast est overkill pour ce genre d'utilisation
public class UserRepositorySimpleAdapter implements UserRepository {

	private final Map<String, User> inMemoryDb = new HashMap<>();

	@Override
	public User create(final User user) {
		inMemoryDb.put(user.getId(), user);
		return user;
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
