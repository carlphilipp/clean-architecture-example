package com.slalom.example.vertx.controller;

import com.slalom.example.usecase.CreateUser;
import com.slalom.example.usecase.FindUser;
import com.slalom.example.usecase.LoginUser;
import com.slalom.example.vertx.model.UserWeb;
import com.slalom.example.vertx.utils.JsonCollectors;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class VertxUserController {

	private final CreateUser createUser;
	private final FindUser findUser;
	private final LoginUser loginUser;

	public VertxUserController(CreateUser createUser, FindUser findUser, LoginUser loginUser) {
		this.createUser = createUser;
		this.findUser = findUser;
		this.loginUser = loginUser;
	}

	public void createUser(RoutingContext routingContext) {
		var response = routingContext.response();
		var body = routingContext.getBody();
		if (isNull(body)) {
			sendError(400, response);
		} else {
			var userWeb = body.toJsonObject().mapTo(UserWeb.class);
			var user = createUser.create(userWeb.toUser());
			var result = JsonObject.mapFrom(UserWeb.toUserWeb(user));
			sendSuccess(result, response);
		}
	}

	public void login(RoutingContext routingContext) {
		var response = routingContext.response();
		var email = routingContext.request().getParam("email");
		var password = routingContext.request().getParam("password");
		if (email == null || password == null) {
			sendError(400, response);
		} else {
			var user = loginUser.login(email, password);
			var result = JsonObject.mapFrom(UserWeb.toUserWeb(user));
			sendSuccess(result, response);
		}
	}

	public void findUser(final RoutingContext routingContext) {
		var response = routingContext.response();
		var userId = routingContext.request().getParam("userId");
		if (userId == null) {
			sendError(400, response);
		} else {
			var user = findUser.findById(userId);
			if (user.isPresent()) {
				var result = JsonObject.mapFrom(UserWeb.toUserWeb(user.get()));
				sendSuccess(result, response);
			} else {
				sendError(404, response);
			}
		}
	}

	public void findAllUsers(final RoutingContext routingContext) {
		var response = routingContext.response();
		var users = findUser.findAll();
		var result = users.stream()
			.map(user -> JsonObject.mapFrom(UserWeb.toUserWeb(user)))
			.collect(JsonCollectors.toJsonArray());
		response
			.putHeader("content-type", "application/json")
			.end(result.encodePrettily());
	}


	private boolean isNull(final Buffer buffer) {
		return buffer == null || "".equals(buffer.toString());
	}

	private void sendError(int statusCode, HttpServerResponse response) {
		response
			.putHeader("content-type", "application/json")
			.setStatusCode(statusCode)
			.end();
	}

	private void sendSuccess(JsonObject body, HttpServerResponse response) {
		response
			.putHeader("content-type", "application/json")
			.end(body.encodePrettily());
	}
}
