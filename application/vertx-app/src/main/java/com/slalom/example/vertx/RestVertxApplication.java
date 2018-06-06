package com.slalom.example.vertx;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.slalom.example.core.spi.IdGenerator;
import com.slalom.example.core.spi.PasswordEncoder;
import com.slalom.example.core.spi.UserRepository;
import com.slalom.example.core.usecases.CreateUser;
import com.slalom.example.core.usecases.FindUser;
import com.slalom.example.core.usecases.LoginUser;
import com.slalom.example.db.InMemoryUserRepository;
import com.slalom.example.encoder.Sha256PasswordEncoder;
import com.slalom.example.jug.JugAdapter;
import com.slalom.example.vertx.model.UserWeb;
import com.slalom.example.vertx.utils.JsonCollectors;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RestVertxApplication extends AbstractVerticle {

	private final UserRepository userRepository = new InMemoryUserRepository();
	private final IdGenerator idGenerator = new JugAdapter();
	private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();
	private final CreateUser createUser = new CreateUser(userRepository, passwordEncoder, idGenerator);
	private final FindUser findUser = new FindUser(userRepository);
	private final LoginUser loginUser = new LoginUser(userRepository, passwordEncoder);

	@Override
	public void start() {
		Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		var router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/users").handler(this::createUser);
		router.get("/login").handler(this::login);
		router.get("/users/:userId").handler(this::findUser);
		router.get("/users").handler(this::findAllUser);

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	private void createUser(final RoutingContext routingContext) {
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

	private void login(final RoutingContext routingContext) {
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

	private void findUser(final RoutingContext routingContext) {
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

	private void findAllUser(final RoutingContext routingContext) {
		var response = routingContext.response();
		var users = findUser.findAllUsers();
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

	public static void main(final String[] args) {
		Launcher.executeCommand("run", RestVertxApplication.class.getName());
	}
}
