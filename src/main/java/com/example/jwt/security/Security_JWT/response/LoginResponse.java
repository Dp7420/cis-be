package com.example.jwt.security.Security_JWT.response;

import com.example.jwt.security.Security_JWT.entity.Role;
import com.example.jwt.security.Security_JWT.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class LoginResponse {

	private Long id;

	private String token;

	private String type = "Bearer";

	private Role role;

	private Status status;

	private String userName;

	@JsonIgnore
	private String password;

	private String message;

	private String companyEmail;

	private String designation;

	private String email;

	private Long mobile;

}
