package com.slalom.example.spring.db.repository;

import com.slalom.example.spring.db.model.UserDb;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<UserDb, String> {
	Optional<UserDb> findOneByEmail(String email);
}
