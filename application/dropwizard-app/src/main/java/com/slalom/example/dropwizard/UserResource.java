package com.slalom.example.dropwizard;

import com.slalom.example.core.usecase.CreateUser;
import com.slalom.example.core.usecase.FindUser;
import com.slalom.example.core.usecase.LoginUser;
import com.slalom.example.dropwizard.model.UserWeb;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private final CreateUser createUser;
	private final FindUser findUser;
	private final LoginUser loginUser;

	@Inject
	public UserResource(final CreateUser createUser, final FindUser findUser, final LoginUser loginUser) {
		this.createUser = createUser;
		this.findUser = findUser;
		this.loginUser = loginUser;
	}

	@POST
	@Path("/users")
	public UserWeb createUser(final UserWeb userWeb) {
		return UserWeb.toUserWeb(createUser.create(userWeb.toUser()));
	}

	@GET
	@Path("/login")
	public UserWeb login(@QueryParam("email") final String email, @QueryParam("password") final String password) {
		return UserWeb.toUserWeb(loginUser.login(email, password));
	}

	@GET
	@Path("/users/{userId}")
	public UserWeb getUser(@PathParam("userId") final String userId) {
		return UserWeb.toUserWeb(findUser.findById(userId).orElseThrow(() -> new RuntimeException("user not found")));
	}

	@GET
	@Path("/users")
	public List<UserWeb> allUsers() {
		return findUser.findAllUsers()
			.stream()
			.map(UserWeb::toUserWeb)
			.collect(Collectors.toList());
	}
}
