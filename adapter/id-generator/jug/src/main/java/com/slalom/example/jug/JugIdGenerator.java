package com.slalom.example.jug;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import com.slalom.example.domain.port.IdGenerator;

public class JugIdGenerator implements IdGenerator {

	@Override
	public String generate() {
		return generator().generate().toString().replaceAll("-", "");
	}

	private static NoArgGenerator generator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}
}
