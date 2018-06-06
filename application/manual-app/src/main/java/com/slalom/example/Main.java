package com.slalom.example;

import com.slalom.example.domain.entities.User;
import com.slalom.example.domain.usecases.CreateUser;
import com.slalom.example.domain.usecases.FindUser;
import com.slalom.example.domain.usecases.LoginUser;
import com.slalom.example.db.InMemoryUserRepository;
import com.slalom.example.encoder.Sha256PasswordEncoder;
import com.slalom.example.jug.JugAdapter;

public class Main {
	public static void main(String[] args) {
		// Setup
		var userRepository = new InMemoryUserRepository();
		var idGenerator = new JugAdapter();
		var passwordEncoder = new Sha256PasswordEncoder();
		var createUser = new CreateUser(userRepository, passwordEncoder, idGenerator);
		var findUser = new FindUser(userRepository);
		var loginUser = new LoginUser(userRepository, passwordEncoder);
		var user = User.builder()
			.email("john.doe@gmail.com")
			.password("mypassword")
			.lastName("doe")
			.firstName("john")
			.build();

		// Create a user
		var actualCreateUser = createUser.create(user);
		System.out.println("User created with id " + actualCreateUser.getId());

		// Find a user by id
		var actualFindUser = findUser.findById(actualCreateUser.getId());
		System.out.println("Found user with id " + actualFindUser.get().getId());

		// List all users
		var users = findUser.findAllUsers();
		System.out.println("List all users: " + users);

		// Login
		loginUser.login("john.doe@gmail.com", "mypassword");
		System.out.println("Allowed to login with email 'john.doe@gmail.com' and password  'mypassword'");
	}
}
