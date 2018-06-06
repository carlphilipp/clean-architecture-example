module slalom.example.controller.spring {
	exports com.slalom.example.spring.controller;
	exports com.slalom.example.spring.models;

	requires slalom.example.usecases;
	requires slalom.example.domain;
	requires spring.beans;
	requires spring.web;
	requires com.fasterxml.jackson.annotation;
}
