package com.slalom.example.domain.port;

import com.slalom.example.domain.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

	User create(User user);

	Optional<User> findById(String id);

	Optional<User> findByEmail(String email);

	List<User> findAllUsers();
}
