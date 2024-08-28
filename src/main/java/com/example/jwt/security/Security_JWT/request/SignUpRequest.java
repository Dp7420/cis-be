package com.example.jwt.security.Security_JWT.request;

import javax.validation.constraints.Email;

import com.example.jwt.security.Security_JWT.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

	private String fullName;

	private String userName;

	@Email
	private String email;

	private Long mobile;

	private String password;

	private String role;

	private String designation;

	private String status;

	private Company company;

}
