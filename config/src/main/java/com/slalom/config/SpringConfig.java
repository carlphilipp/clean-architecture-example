package com.slalom.config;

import com.slalom.example.db.hazelcast.HazelcastUserRepository;
import com.slalom.example.domain.ports.PasswordEncoder;
import com.slalom.example.domain.ports.UserRepository;
import com.slalom.example.domain.usecases.CreateUser;
import com.slalom.example.domain.usecases.FindUser;
import com.slalom.example.domain.usecases.LoginUser;
import com.slalom.example.encoder.Sha256PasswordEncoder;
import com.slalom.example.uuid.UuidGenerator;

public class SpringConfig {

	private final UserRepository userRepository = new HazelcastUserRepository();
	private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();

	public CreateUser createUser() {
		return new CreateUser(userRepository, passwordEncoder, new UuidGenerator());
	}

	public FindUser findUser() {
		return new FindUser(userRepository);
	}

	public LoginUser loginUser() {
		return new LoginUser(userRepository, passwordEncoder);
	}
}
