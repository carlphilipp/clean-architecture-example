package com.slalom.example.spring.controller;

import com.slalom.example.controller.UserController;
import com.slalom.example.controller.model.UserWeb;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringUserController {

	private final UserController controller;

	@Autowired
	public SpringUserController(final UserController controller) {
		this.controller = controller;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public UserWeb createUser(@RequestBody final UserWeb userWeb) {
		return controller.createUser(userWeb);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public UserWeb login(@RequestParam("email") final String email, @RequestParam("password") final String password) {
		return controller.login(email, password);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public UserWeb getUser(@PathVariable("userId") final String userId) {
		return controller.getUser(userId);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserWeb> allUsers() {
		return controller.allUsers();
	}
}
