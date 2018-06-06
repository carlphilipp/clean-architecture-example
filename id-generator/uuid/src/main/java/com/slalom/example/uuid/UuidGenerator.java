package com.slalom.example.uuid;

import com.slalom.example.core.spi.IdGenerator;
import java.util.UUID;

public class UuidGenerator implements IdGenerator {

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}
}
