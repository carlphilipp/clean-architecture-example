package com.slalom.config;

import com.slalom.example.db.InMemoryUserRepository;
import com.slalom.example.domain.port.IdGenerator;
import com.slalom.example.domain.port.PasswordEncoder;
import com.slalom.example.domain.port.UserRepository;
import com.slalom.example.encoder.Sha256PasswordEncoder;
import com.slalom.example.jug.JugIdGenerator;
import com.slalom.example.usecase.CreateUser;
import com.slalom.example.usecase.FindUser;
import com.slalom.example.usecase.LoginUser;

public class ManualConfig {
	private final UserRepository userRepository = new InMemoryUserRepository();
	private final IdGenerator idGenerator = new JugIdGenerator();
	private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();

	public CreateUser createUser() {
		return new CreateUser(userRepository, passwordEncoder, idGenerator);
	}

	public FindUser findUser() {
		return new FindUser(userRepository);
	}

	public LoginUser loginUser() {
		return new LoginUser(userRepository, passwordEncoder);
	}
}
