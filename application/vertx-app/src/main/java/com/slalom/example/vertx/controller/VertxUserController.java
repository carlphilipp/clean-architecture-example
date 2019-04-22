package com.slalom.example.vertx.controller;

import com.slalom.example.controller.UserController;
import com.slalom.example.controller.model.UserWeb;
import com.slalom.example.vertx.utils.JsonCollectors;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class VertxUserController {

	private final UserController controller;

	public VertxUserController(final UserController controller) {
		this.controller = controller;
	}

	public void createUser(final RoutingContext routingContext) {
		var response = routingContext.response();
		var body = routingContext.getBody();
		if (isNull(body)) {
			sendError(400, response);
		} else {
			var userWeb = body.toJsonObject().mapTo(UserWeb.class);
			var user = controller.createUser(userWeb);
			var result = JsonObject.mapFrom(user);
			sendSuccess(result, response);
		}
	}

	public void login(final RoutingContext routingContext) {
		var response = routingContext.response();
		var email = routingContext.request().getParam("email");
		var password = routingContext.request().getParam("password");
		if (email == null || password == null) {
			sendError(400, response);
		} else {
			var user = controller.login(email, password);
			var result = JsonObject.mapFrom(user);
			sendSuccess(result, response);
		}
	}

	public void findUser(final RoutingContext routingContext) {
		var response = routingContext.response();
		var userId = routingContext.request().getParam("userId");
		if (userId == null) {
			sendError(400, response);
		} else {
			try {
				var user = controller.getUser(userId);
				var result = JsonObject.mapFrom(user);
				sendSuccess(result, response);
			} catch (RuntimeException e) {
				sendError(404, response);
			}
		}
	}

	public void findAllUser(final RoutingContext routingContext) {
		var response = routingContext.response();
		var users = controller.allUsers();
		var result = users.stream()
			.map(JsonObject::mapFrom)
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
