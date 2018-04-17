package com.slalom.example;

import com.slalom.example.core.entity.User;
import com.slalom.example.core.spi.IdGenerator;
import com.slalom.example.core.spi.PasswordEncoder;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.core.usecase.CreateUser;
import com.slalom.example.core.usecase.FindUser;
import com.slalom.example.core.usecase.LoginUser;
import com.slalom.example.db.UserRepositorySimpleAdapter;
import com.slalom.example.encoder.PasswordEncoderAdapter;
import com.slalom.example.jug.JugAdapter;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		final UserRepository userRepository = new UserRepositorySimpleAdapter();
		final IdGenerator idGenerator = new JugAdapter();
		final PasswordEncoder passwordEncoder = new PasswordEncoderAdapter();
		final CreateUser createUser = new CreateUser(userRepository, passwordEncoder, idGenerator);
		final FindUser findUser = new FindUser(userRepository);
		final LoginUser loginUser = new LoginUser(userRepository, passwordEncoder);
		final User user = new User("email@gmail.com", "mypassword");

		final User actual = createUser.create(user);
		System.out.println(actual);

		final List<User> users = findUser.findAllUsers();
		System.out.println(users);

		final User loggedInUser = loginUser.login("email@gmail.com", "mypassword");
		System.out.println(loggedInUser);
	}
}
