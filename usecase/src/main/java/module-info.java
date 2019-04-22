module slalom.example.usecase {
	exports com.slalom.example.usecase;
	exports com.slalom.example.usecase.exception;
	exports com.slalom.example.usecase.port;

	requires slalom.example.domain;
	requires org.apache.commons.lang3;
}
