package com.slalom.example.usecase;

import com.slalom.example.domain.entity.User;
import com.slalom.example.domain.exception.NotAllowedException;
import com.slalom.example.domain.port.PasswordEncoder;
import com.slalom.example.domain.port.UserRepository;

public final class LoginUser {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public LoginUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User login(String email, String password) {
		var user = userRepository.findByEmail(email).orElseThrow(() -> new NotAllowedException("Not allowed"));
		var hashedPassword = passwordEncoder.encode(email + password);
		if (!user.getPassword().equals(hashedPassword)) throw new NotAllowedException("Not allowed");
		return user;
	}
}
