module slalom.example.controller.vertx {
	exports com.slalom.example.vertx.controller;
	exports com.slalom.example.vertx.models;

	requires slalom.example.usecases;
	requires slalom.example.domain;
	requires vertx.web;
	requires vertx.core;
	requires com.fasterxml.jackson.annotation;
}
