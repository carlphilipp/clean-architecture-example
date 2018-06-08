module slalom.example.spring {
	requires slalom.example.config;
	requires slalom.example.usecases;
	requires slalom.example.controller.spring;

	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;

	exports com.slalom.example.spring;
	exports com.slalom.example.spring.config;
	opens com.slalom.example.spring.config to spring.core;
}
