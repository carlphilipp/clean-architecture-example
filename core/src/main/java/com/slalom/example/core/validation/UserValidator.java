// todo : je rangerai la validation dans le répertoire use cases dans un sous répertoire validators par exemple
package com.slalom.example.core.validation;

import com.slalom.example.core.entity.User;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class UserValidator {

	public static void validateCreateUser(final User user) {
		if (user == null) throw new RuntimeException("User should not be null");
		if (isBlank(user.getEmail())) throw new RuntimeException("Email should not be null");
		if (isBlank(user.getFirstName())) throw new RuntimeException("First name should not be null");
		if (isBlank(user.getLastName())) throw new RuntimeException("Last name should not be null");
	}

	private UserValidator() {

	}
}
