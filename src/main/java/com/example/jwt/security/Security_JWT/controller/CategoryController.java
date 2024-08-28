package com.example.jwt.security.Security_JWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.security.Security_JWT.entity.Categories;
import com.example.jwt.security.Security_JWT.service.CategoriesService;
import com.example.jwt.security.Security_JWT.service.OtpService;

@RestController
//@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoriesService categoryService;
	
	@Autowired
	private OtpService otpService;

	@PostMapping("/verifyEmail")
	public ResponseEntity<?> emailValidation(@RequestParam String email) {
		return ResponseEntity.ok(otpService.sendOtpRegister(email));
	}
	
	@PostMapping("/requestOtp")
	public ResponseEntity<?> sendOtpToMail(@RequestParam String email) {
		return ResponseEntity.ok(otpService.sendOtpRegister(email));
	}

	@PostMapping("/verifyOtp")
	public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
		return ResponseEntity.ok(otpService.verifyOtp(email, otp));
	}
	
	
	@PostMapping("/user/save") 
	public Categories saveCategory(@RequestBody Categories c) {
		return categoryService.createCategory(c);
	}

	@GetMapping("/user/fetchall") 
	public List<Categories> gettingAllCategories() {
		return categoryService.retriveAllCategories();
	}

	@GetMapping("/user/fetchbyid/{id}")
	public Categories getCategoriesById(@PathVariable Long id) {
		return categoryService.retriveCategoryById(id);
	}
	
	@GetMapping("user/fetchbycid/{companyid}")
	public List<Categories> fetchCategoriesByCid(@PathVariable("companyId")Long companyId){
		return categoryService.findByCompanyId(companyId);
	}

	@PutMapping("/admin/update/{id}") 
	public Categories updateCategories(@RequestBody Categories c, @PathVariable("id") Long id) {
		return categoryService.editCategory(c,id);
	}

	@DeleteMapping("/admin/delete/{id}") 
	public void delCardById(@PathVariable("id") Long id) {
		categoryService.removeCategoriesById(id);
	}

	@DeleteMapping("/admin/delete/all") 
	public void delAllCards() {
		categoryService.removeAllCategories();
	}
}
