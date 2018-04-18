package com.slalom.example.dropwizard;

import com.slalom.example.core.spi.IdGenerator;
import com.slalom.example.core.spi.PasswordEncoder;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.core.usecase.CreateUser;
import com.slalom.example.core.usecase.FindUser;
import com.slalom.example.core.usecase.LoginUser;
import com.slalom.example.db.UserRepositorySimpleAdapter;
import com.slalom.example.dropwizard.config.Config;
import com.slalom.example.encoder.PasswordEncoderAdapter;
import com.slalom.example.jug.JugAdapter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Config> {

	public static void main(String[] args) throws Exception {
		new Application().run(args);
	}

	@Override
	public void run(Config configuration, Environment environment) {
		environment.jersey().register(new AbstractBinder() {
			@Override
			protected void configure() {
				UserRepository userRepository = new UserRepositorySimpleAdapter();
				IdGenerator idGenerator = new JugAdapter();
				PasswordEncoder passwordEncoder = new PasswordEncoderAdapter();

				CreateUser createUser = new CreateUser(userRepository, passwordEncoder, idGenerator);
				FindUser findUser = new FindUser(userRepository);
				LoginUser loginUser = new LoginUser(userRepository, passwordEncoder);
				bind(createUser).to(CreateUser.class);
				bind(findUser).to(FindUser.class);
				bind(loginUser).to(LoginUser.class);
			}
		});
	}
}
