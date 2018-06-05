package com.slalom.example.core.entity;

public class User {

	private String id;
	private String email;
	private String password;
	private String lastName;
	private String firstName;

	private User(final String id, final String email, final String password, final String lastName, final String firstName) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	// todo : convention de java d'avoir le builder dans la classe ?
	// todo : perso je sors les builders pour les mettre à côté des use cases (c'est totalement arbitraire)
	public static UserBuilder builder() {
		return new UserBuilder();
	}

	public static class UserBuilder {
		private String id;
		private String email;
		private String password;
		private String lastName;
		private String firstName;

		UserBuilder() {
		}

		public UserBuilder id(final String id) {
			this.id = id;
			return this;
		}

		public UserBuilder email(final String email) {
			this.email = email;
			return this;
		}

		public UserBuilder password(final String password) {
			this.password = password;
			return this;
		}

		public UserBuilder lastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		public User build() {
			return new User(id, email, password, lastName, firstName);
		}
	}

	// todo : gros :+1: pour le value object ici !
	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "User{" +
			"id='" + id + '\'' +
			", email='" + email + '\'' +
			", password='" + password + '\'' +
			", lastName='" + lastName + '\'' +
			", firstName='" + firstName + '\'' +
			'}';
	}
}
