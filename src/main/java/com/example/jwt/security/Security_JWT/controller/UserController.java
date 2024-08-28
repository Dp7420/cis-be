package com.example.jwt.security.Security_JWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.security.Security_JWT.entity.Categories;
import com.example.jwt.security.Security_JWT.entity.Customers;
import com.example.jwt.security.Security_JWT.entity.Role;
import com.example.jwt.security.Security_JWT.entity.Services;
import com.example.jwt.security.Security_JWT.entity.User;
import com.example.jwt.security.Security_JWT.service.AuthenticationService;
import com.example.jwt.security.Security_JWT.service.CategoriesService;
import com.example.jwt.security.Security_JWT.service.CustomerService;
import com.example.jwt.security.Security_JWT.service.OtpService;
import com.example.jwt.security.Security_JWT.service.ServicesService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private CategoriesService categoryService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ServicesService servicesService;

	@Autowired
	private OtpService otpService;

	@GetMapping("/getbyid/{id}")
	public User retriveUserbyId(@PathVariable("id") Long id) {
		return authService.getByIdUser(id);
	}

	@GetMapping("/getall/all")
	public List<User> retrieveByRoleAdmin(@RequestParam String role) {
		if ("ADMIN".equalsIgnoreCase(role)) {
			return authService.getUserByRole(Role.ADMIN);
		} else if ("USER".equalsIgnoreCase(role)) {
			return authService.getUserByRole(Role.USER);
		} else {
			throw new IllegalArgumentException("Invalid role: " + role);
		}
	}

	@GetMapping("/categories/fetchall")
	public List<Categories> gettingAllCategories() {
		return categoryService.retriveAllCategories();
	}

	@GetMapping("/category/fetchbyid/{id}")
	public Categories getCategoriesById(@PathVariable Long id) {
		return categoryService.retriveCategoryById(id);
	}

	@GetMapping("/customer/fetchbyid/{id}")
	public Customers retriveCustomerById(@PathVariable("id") Long id) {
		return customerService.getCustomerById(id);
	}

	@GetMapping("/customer/fetchall")
	public List<Customers> retriveAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/service/fetchall")
	public List<Services> getAllServices() {
		return servicesService.getAllServices();
	}

	@GetMapping("/service/{serviceId}")
	public Services getServiceById(@PathVariable Long serviceId) {
		return servicesService.getServiceById(serviceId);
	}

	@GetMapping("/service/category/{categoryId}")
	public List<Services> getServicesByCategoryId(@PathVariable Long categoryId) {
		return servicesService.findServicesByCategoryId(categoryId);
	}

	@PostMapping("/verifyEmail")
	public ResponseEntity<?> emailValidation(@RequestParam String email) {
		return ResponseEntity.ok(otpService.sendOtpRegister(email));
	}

	@PostMapping("/requestOtp")
	public ResponseEntity<?> sendOtpToMail(@RequestParam String email) {
		return ResponseEntity.ok(otpService.sendOtp(email));
	}

	@PostMapping("/verifyOtp")
	public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
		return ResponseEntity.ok(otpService.verifyOtp(email, otp));
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String otp,
			@RequestParam String newPassword) {
		return ResponseEntity.ok(otpService.changePassword(email, otp, newPassword));
	}

	@GetMapping("/company/{company_id}")
	public ResponseEntity<List<User>> getUsersByCompanyId(@PathVariable Long company_id) {
		List<User> user = authService.findByCompanyId(company_id);
		return ResponseEntity.ok(user);
	}

}
