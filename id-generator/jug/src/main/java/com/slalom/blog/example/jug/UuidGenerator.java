package com.slalom.blog.example.jug;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import com.slalom.example.core.spi.IdGenerator;

public class UuidGenerator implements IdGenerator {

	@Override
	public String generate() {
		return generator().generate().toString();
	}

	private static NoArgGenerator generator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}
}
