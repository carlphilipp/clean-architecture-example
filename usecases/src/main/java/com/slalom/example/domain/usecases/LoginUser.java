package com.slalom.example.domain.usecases;

import com.slalom.example.domain.entities.User;
import com.slalom.example.domain.exceptions.NotAllowedException;
import com.slalom.example.domain.ports.PasswordEncoder;
import com.slalom.example.domain.ports.UserRepository;

public class LoginUser {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public LoginUser(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User login(final String email, final String password) {
		var user = userRepository.findByEmail(email).orElseThrow(() -> new NotAllowedException("Not allowed"));
		var hashedPassword = passwordEncoder.encode(email + password);
		if (!user.getPassword().equals(hashedPassword)) throw new NotAllowedException("Not allowed");
		return user;
	}
}
