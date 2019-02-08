package com.slalom.example.usecase.validator;

import com.slalom.example.domain.entity.User;
import com.slalom.example.domain.exception.UserValidationException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class UserValidator {

	private UserValidator() {}

	public static void validate(User user) {
		if (user == null) throw new UserValidationException("User should not be null");
		if (isBlank(user.getEmail())) throw new UserValidationException("Email should not be blank");
		if (isBlank(user.getFirstName())) throw new UserValidationException("First name should not be blank");
		if (isBlank(user.getLastName())) throw new UserValidationException("Last name should not be blank");
	}
}
