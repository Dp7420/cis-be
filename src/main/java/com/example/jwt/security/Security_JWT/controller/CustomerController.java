package com.example.jwt.security.Security_JWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.security.Security_JWT.dto.CustomerDTO;
import com.example.jwt.security.Security_JWT.entity.Customers;
import com.example.jwt.security.Security_JWT.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
//	@Autowired
//	private ServicesRepository serviceRepo;

	@PostMapping("/user/save")
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerdto) {
	        return new ResponseEntity<>(customerService.createCustomer(customerdto), HttpStatus.CREATED);
	}

	@GetMapping("/user/fetchbyid/{id}")
	public Customers retriveCustomerById(@PathVariable("id") Long id) {
		return customerService.getCustomerById(id);
	}

	@GetMapping("/user/fetchall")
	public List<Customers> retriveAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PutMapping("/admin/updatebyid/{id}")
	public Customers updateCustomerById(@PathVariable("id") Long id, @RequestBody Customers customer) {
		return customerService.editCustomerById(id,customer);
	}
	
	@DeleteMapping("/admin/deletebyid/{id}")
	public void deleteCustomerById(@PathVariable("id") Long id) {
		customerService.removeCustomer(id);
	}
}
