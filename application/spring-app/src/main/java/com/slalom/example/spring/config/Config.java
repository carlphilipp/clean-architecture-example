package com.slalom.example.spring.config;

import com.slalom.example.core.spi.IdGenerator;
import com.slalom.example.core.spi.PasswordEncoder;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.core.usecase.CreateUser;
import com.slalom.example.core.usecase.FindUser;
import com.slalom.example.core.usecase.LoginUser;
import com.slalom.example.db.hazelcast.HazelcastUserRepository;
import com.slalom.example.encoder.Sha256PasswordEncoder;
import com.slalom.example.uuid.UuidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public UserRepository userRepository() {
		return new HazelcastUserRepository();
	}

	@Bean
	public IdGenerator idGenerator() {
		return new UuidGenerator();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Sha256PasswordEncoder();
	}

	@Bean
	public CreateUser createUser(
		final UserRepository userRepository,
		final PasswordEncoder passwordEncoder,
		final IdGenerator idGenerator) {
		return new CreateUser(userRepository, passwordEncoder, idGenerator);
	}

	@Bean
	public FindUser findUser(final UserRepository userRepository) {
		return new FindUser(userRepository);
	}

	@Bean
	public LoginUser loginUser(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
		return new LoginUser(userRepository, passwordEncoder);
	}

}
