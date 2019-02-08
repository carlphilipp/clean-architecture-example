package com.slalom.example.spring.controller;

import com.slalom.example.usecase.CreateUser;
import com.slalom.example.usecase.FindUser;
import com.slalom.example.usecase.LoginUser;
import com.slalom.example.spring.model.UserWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

	private final CreateUser createUser;
	private final FindUser findUser;
	private final LoginUser loginUser;

	@Autowired
	public UserController(CreateUser createUser, FindUser findUser, LoginUser loginUser) {
		this.createUser = createUser;
		this.findUser = findUser;
		this.loginUser = loginUser;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public UserWeb createUser(@RequestBody final UserWeb userWeb) {
		var user = userWeb.toUser();
		return UserWeb.toUserWeb(createUser.create(user));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public UserWeb login(@RequestParam("email") final String email, @RequestParam("password") final String password) {
		return UserWeb.toUserWeb(loginUser.login(email, password));
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public UserWeb getUser(@PathVariable("userId") final String userId) {
		return UserWeb.toUserWeb(findUser.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserWeb> getAllUsers() {
		return findUser.findAll()
			.stream()
			.map(UserWeb::toUserWeb)
			.collect(Collectors.toList());
	}
}
