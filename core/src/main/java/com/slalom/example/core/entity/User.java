package com.slalom.example.core.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
	public String id;
	public String email;
	public String password;
	public Role role;
}
