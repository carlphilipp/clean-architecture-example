module slalom.example.spring {
	requires slalom.example.config;
	requires slalom.example.usecase;
	requires slalom.example.controller;
	requires spring.web;
	requires spring.beans;

	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires com.fasterxml.jackson.databind;
	requires jackson.annotations;

	exports com.slalom.example.spring;
	exports com.slalom.example.spring.config;
	opens com.slalom.example.spring.config to spring.core;
}
