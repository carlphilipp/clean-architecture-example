package com.slalom.example.uuid;

import com.slalom.example.core.spi.IdGenerator;
import java.util.UUID;

// todo : sans le adapter est le nom de l'adapter est nickel ici
public class UuidGeneratorAdapter implements IdGenerator {

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}
}
