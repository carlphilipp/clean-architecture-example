module slalom.example.controller {
	exports com.slalom.example.controller;
	exports com.slalom.example.controller.model;

	requires slalom.example.usecase;
	requires slalom.example.domain;
}
