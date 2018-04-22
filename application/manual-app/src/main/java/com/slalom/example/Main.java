package com.slalom.example;

import com.slalom.example.core.entity.User;
import com.slalom.example.core.usecase.CreateUser;
import com.slalom.example.core.usecase.FindUser;
import com.slalom.example.core.usecase.LoginUser;
import com.slalom.example.db.UserRepositorySimpleAdapter;
import com.slalom.example.encoder.PasswordEncoderAdapter;
import com.slalom.example.jug.JugAdapter;

public class Main {
	public static void main(String[] args) {
		var userRepository = new UserRepositorySimpleAdapter();
		var idGenerator = new JugAdapter();
		var passwordEncoder = new PasswordEncoderAdapter();
		var createUser = new CreateUser(userRepository, passwordEncoder, idGenerator);
		var findUser = new FindUser(userRepository);
		var loginUser = new LoginUser(userRepository, passwordEncoder);
		var user = User.builder()
			.email("john.doe@gmail.com")
			.password("mypassword")
			.lastName("john")
			.firstName("doe")
			.build();

		var actual = createUser.create(user);
		System.out.println(actual);

		var users = findUser.findAllUsers();
		System.out.println(users);

		var loggedInUser = loginUser.login("john.doe@gmail.com", "mypassword");
		System.out.println(loggedInUser);
	}
}
