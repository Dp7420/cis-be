package com.example.jwt.security.Security_JWT.dto;

import com.example.jwt.security.Security_JWT.entity.Categories;

import lombok.Data;

@Data
public class ServicesDTo {

	private String serviceName;

	private String serviceShortName;

	private Categories category;
}
