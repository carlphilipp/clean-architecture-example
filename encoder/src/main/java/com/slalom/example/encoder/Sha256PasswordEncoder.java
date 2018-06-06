package com.slalom.example.encoder;

import com.slalom.example.core.spi.PasswordEncoder;
import org.apache.commons.codec.digest.DigestUtils;

public class Sha256PasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(final String str) {
		return DigestUtils.sha256Hex(str);
	}
}
