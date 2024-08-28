package com.example.jwt.security.Security_JWT.dto;

import java.util.Set;

import javax.validation.constraints.Email;

import com.example.jwt.security.Security_JWT.entity.Services;

import lombok.Data;

@Data
public class CustomerDTO {

	private String primaryName;

	private String secondaryName;

	private Long primaryMobile;

	@Email
	private String primaryEmail;

	private Long secondaryMobile;

	@Email
	private String secondaryEmail;

	private String cin;

	private Long comTelephone;

	private String mainPerson;

	private String designation;

	private String pan;

	private String gst;

	private String companyName;

	@Email
	private String companyEmail;

	private String address;

	private String website;

	private Long contactNo;

	private String state;

	private Long pincode;

	private Set<Services> services;

}
