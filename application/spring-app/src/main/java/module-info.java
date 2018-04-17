module com.slalom.example.spring {
	requires com.slalom.example.core;
	requires com.slalom.example.jug;
	requires com.slalom.example.db.simple;
	requires com.slalom.example.encoder;

	requires spring.boot;
	requires spring.boot.starter.web;
	requires spring.boot.autoconfigure;
	requires spring.beans;
	requires spring.context;
	requires spring.web;
	requires jackson.annotations;
	requires java.sql;

	exports com.slalom.example.spring;
	exports com.slalom.example.spring.model;
	exports com.slalom.example.spring.config;
	opens com.slalom.example.spring.config to spring.core;
}
