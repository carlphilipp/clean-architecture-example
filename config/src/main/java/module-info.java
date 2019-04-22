module slalom.example.config {
	exports com.slalom.config;

	requires slalom.example.usecase;
	requires slalom.example.domain;
	requires slalom.example.jug;
	requires slalom.example.uuid;
	requires slalom.example.db.simple;
	requires slalom.example.db.hazelcast;
	requires slalom.example.encoder;
}
