package com.example.jwt.security.Security_JWT.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.security.Security_JWT.dto.CompanyDTO;
import com.example.jwt.security.Security_JWT.entity.Company;
import com.example.jwt.security.Security_JWT.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/user/fetchall")
	public List<Company> getAllOrganisations() {
		return companyService.findAll();
	}

	@GetMapping("/user/fetchbyid/{id}")
	public ResponseEntity<Company> getOrganisationById(@PathVariable Long id) {
		Optional<Company> company = companyService.findById(id);
		return company.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/user/save")
	public Company createOrganisation(@RequestBody CompanyDTO organisation)
			throws Exception {
		return companyService.save(organisation);
	}

	@PutMapping("/admin/update/{id}")
	public ResponseEntity<Company> updateOrganisation(@PathVariable Long id, @RequestBody Company organisationDetails) {
		try {
			Company updatedOrganisation = companyService.updateCompany(id, organisationDetails);
			return ResponseEntity.ok(updatedOrganisation);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
		companyService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	
}
