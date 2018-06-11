module slalom.example.controller.vertx {
	exports com.slalom.example.vertx.controller;
	exports com.slalom.example.vertx.model;

	requires slalom.example.usecase;
	requires slalom.example.domain;
	requires vertx.web;
	requires vertx.core;
	requires jackson.annotations;
}
