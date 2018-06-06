module slalom.example.spring {
	requires slalom.example.config;
	requires slalom.example.usecases;
	requires slalom.example.controller.spring;

	requires spring.boot;
	requires spring.boot.starter.web;
	requires spring.boot.autoconfigure;
	requires spring.beans;
	requires spring.context;
	requires spring.web;
	requires jackson.annotations;

	exports com.slalom.example.spring;
	exports com.slalom.example.spring.config;
	opens com.slalom.example.spring.config to spring.core;
}
