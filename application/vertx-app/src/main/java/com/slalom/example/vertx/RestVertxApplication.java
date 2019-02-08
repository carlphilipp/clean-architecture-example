package com.slalom.example.vertx;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.slalom.config.VertxConfig;
import com.slalom.example.vertx.controller.VertxUserController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class RestVertxApplication extends AbstractVerticle {

	private final VertxUserController userController = new VertxConfig().getVertxUserController();

	@Override
	public void start() {
		Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		var router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/users").handler(userController::createUser);
		router.get("/login").handler(userController::login);
		router.get("/users/:userId").handler(userController::findUser);
		router.get("/users").handler(userController::findAllUsers);

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	public static void main(final String[] args) {
		Launcher.executeCommand("run", RestVertxApplication.class.getName());
	}
}
