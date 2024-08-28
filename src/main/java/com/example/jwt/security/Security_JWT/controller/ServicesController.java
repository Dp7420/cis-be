package com.example.jwt.security.Security_JWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.security.Security_JWT.dto.ServicesDTo;
import com.example.jwt.security.Security_JWT.entity.Services;
import com.example.jwt.security.Security_JWT.service.ServicesService;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

	@Autowired
	private ServicesService servicesService;

	@PostMapping("/user/save")
	public Services createService(@RequestBody ServicesDTo service) {
		return servicesService.createService(service);
	}

	@GetMapping("/user/fetchall")
	public List<Services> getAllServices() {
		return servicesService.getAllServices();
	}

	@GetMapping("/user/{serviceId}")
	public Services getServiceById(@PathVariable Long serviceId) {
		return servicesService.getServiceById(serviceId);
	}

	@GetMapping("/user/category/{categoryId}")
	public List<Services> getServicesByCategoryId(@PathVariable Long categoryId) {
		return servicesService.findServicesByCategoryId(categoryId);
	}

	@PutMapping("/admin/services/{id}")
	public Services updateService(@PathVariable("id") Long id, @RequestBody Services service) {
		return servicesService.updateService(id, service);
	}

	@DeleteMapping("/admin/{id}")
	public void deleteService(@PathVariable Long id) {
		servicesService.deleteService(id);
	}
}
