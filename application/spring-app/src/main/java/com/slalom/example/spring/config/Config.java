package com.slalom.example.spring.config;

import com.slalom.example.core.spi.IdGenerator;
import com.slalom.example.core.spi.PasswordEncoder;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.core.usecase.CreateUser;
import com.slalom.example.core.usecase.FindUser;
import com.slalom.example.core.usecase.LoginUser;
import com.slalom.example.db.UserRepositorySimpleAdapter;
import com.slalom.example.encoder.PasswordEncoderAdapter;
import com.slalom.example.jug.JugAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public UserRepository userRepository() {
		return new UserRepositorySimpleAdapter();
	}

	@Bean
	public IdGenerator idGenerator() {
		return new JugAdapter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoderAdapter();
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
