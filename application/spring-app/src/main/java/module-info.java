module com.slalom.example.spring {
	requires slalom.example.core;
	requires slalom.example.usecases;
	requires slalom.example.uuid;
	requires slalom.example.encoder;
	requires slalom.example.db.hazelcast;

	requires spring.boot;
	requires spring.boot.starter.web;
	requires spring.boot.autoconfigure;
	requires spring.beans;
	requires spring.context;
	requires spring.web;
	requires jackson.annotations;

	exports com.slalom.example.spring;
	exports com.slalom.example.spring.model;
	exports com.slalom.example.spring.config;
	opens com.slalom.example.spring.config to spring.core;
}
