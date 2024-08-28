package com.example.jwt.security.Security_JWT.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.security.Security_JWT.dto.CompanyDTO;
import com.example.jwt.security.Security_JWT.entity.Company;
import com.example.jwt.security.Security_JWT.entity.Status;
import com.example.jwt.security.Security_JWT.entity.User;
import com.example.jwt.security.Security_JWT.repository.UserRepository;
import com.example.jwt.security.Security_JWT.request.SignInRequest;
import com.example.jwt.security.Security_JWT.request.SignUpRequest;
import com.example.jwt.security.Security_JWT.service.AuthenticationService;
import com.example.jwt.security.Security_JWT.service.CompanyService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserRepository ur;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authService.signUp(request));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody SignInRequest request) {
		Optional<User> u = ur.findByEmail(request.getEmail());
		if (u.isEmpty()) {
			return ResponseEntity.badRequest().body("ACCOUNT NOT FOUND");
		}
		User uu = u.get();
		String status = uu.getStatus().name();
		if (status.equalsIgnoreCase(Status.APPROVED.name())) {
			return ResponseEntity.ok(authService.signIn(request));
		} else {
			throw new IllegalArgumentException("PLEASE WAIT FOR A WHILE,ADMIN NOT APPROVED YOU.........");
		}
	}

	@PostMapping("/com/save")
	public Company createCompany(@RequestBody CompanyDTO company) throws Exception {
		return companyService.save(company);
	}

	@GetMapping("/com/fetchall")
	public List<Company> getAllCompanies() {
		return companyService.findAll();
	}

	@GetMapping("/com/fetchbyid/{id}")
	public ResponseEntity<Company> getOrganisationById(@PathVariable Long id) {
		Optional<Company> company = companyService.findById(id);
		return company.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

}
