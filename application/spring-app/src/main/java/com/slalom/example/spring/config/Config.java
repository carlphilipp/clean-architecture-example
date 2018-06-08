package com.slalom.example.spring.config;

import com.slalom.config.SpringConfig;
import com.slalom.example.domain.usecase.CreateUser;
import com.slalom.example.domain.usecase.FindUser;
import com.slalom.example.domain.usecase.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	private final SpringConfig config = new SpringConfig();

	@Bean
	public CreateUser createUser() {
		return config.createUser();
	}

	@Bean
	public FindUser findUser() {
		return config.findUser();
	}

	@Bean
	public LoginUser loginUser() {
		return config.loginUser();
	}
}
