package com.slalom.example.spring.db.model;

import com.slalom.example.core.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class UserDb {

	@Id
	private String id;
	@Column(unique=true)
	private String email;
	private String password;
	private String lastName;
	private String firstName;
	private String role;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public User toUser() {
		return new User(id, email, password, lastName, firstName);
	}

	public static UserDb from(final User user) {
		final UserDb userDb = new UserDb();
		userDb.setId(user.getId());
		userDb.setEmail(user.getEmail());
		userDb.setPassword(user.getPassword());
		userDb.setFirstName(user.getFirstName());
		userDb.setLastName(user.getLastName());
		userDb.setRole(user.getRole().name().toLowerCase());
		return userDb;
	}
}
