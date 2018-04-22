package com.slalom.example.spring.db;

import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.spring.db.model.UserDb;
import com.slalom.example.spring.db.repository.UserCrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserAdapter implements UserRepository {

	private final UserCrudRepository repository;

	public UserAdapter(final UserCrudRepository repository) {
		this.repository = repository;
	}

	@Override
	public User create(final User user) {
		return repository.save(UserDb.from(user)).toUser();
	}

	@Override
	public Optional<User> findById(final String id) {
		return repository.findById(id).map(UserDb::toUser);
	}

	@Override
	public Optional<User> findByEmail(final String email) {
		return repository.findOneByEmail(email).map(UserDb::toUser);
	}

	@Override
	public List<User> findAllUsers() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
			.map(UserDb::toUser)
			.collect(Collectors.toList());
	}
}
