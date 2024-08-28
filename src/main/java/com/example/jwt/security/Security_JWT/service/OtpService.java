package com.example.jwt.security.Security_JWT.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt.security.Security_JWT.entity.User;
import com.example.jwt.security.Security_JWT.repository.UserRepository;

@Service
public class OtpService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserRepository ur;

	@Autowired
	private PasswordEncoder encoder;

	private Map<String, String> otpStorage = new HashMap<>();

	public String generateOtp(String identifier) {
		Random random = new Random();
		String otp = String.format("%06d", random.nextInt(999999));
		otpStorage.put(identifier, otp);
		return otp;
	}

	public boolean validateOtp(String identifier, String otp) {
		return otp.equals(otpStorage.get(identifier));
	}

	public void sendOtpEmail(String email, String otp) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("skumarneelapu@gmail.com");
			message.setTo(email);
			message.setSubject("Your OTP Code");
			message.setText("Your OTP code is: " + otp);
			mailSender.send(message);
			System.out.println("Mail sent successfully to " + email);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error sending email: " + e.getMessage());
		}
	}

	public ResponseEntity<?> sendOtpRegister(String email) {
		String otp = generateOtp(email);
		if (email.contains("@")) {
			sendOtpEmail(email, otp);
		} else {
			ResponseEntity.badRequest().body("Invalid Email.....");
		}
		return ResponseEntity.ok("OTP sent");
	}

	public ResponseEntity<?> sendOtp(String email) {
		Optional<User> userOpt = ur.findByEmail(email);
		if (userOpt.isEmpty()) {
			return ResponseEntity.badRequest().body("User not found with " + email);
		}
		User user = userOpt.get();
		String otp = generateOtp(email);
		if (email.contains("@")) {
			sendOtpEmail(user.getEmail(), otp);
		} else {
			ResponseEntity.badRequest().body("Invalid Email.....");
		}
		return ResponseEntity.ok("OTP sent");
	}

	public ResponseEntity<?> verifyOtp(String email, String otp) {
		boolean isValid = validateOtp(email, otp);
		if (isValid) {
			return ResponseEntity.ok("OTP verified");
		} else {
			return ResponseEntity.badRequest().body("Invalid OTP");
		}
	}

	public ResponseEntity<?> changePassword(String email, String otp, String newPassword) {
		boolean isValid = validateOtp(email, otp);
		if (!isValid) {
			return ResponseEntity.badRequest().body("Invalid OTP");
		}

		Optional<User> userOpt = ur.findByEmail(email);
		if (!userOpt.isPresent()) {
			userOpt = ur.findByEmail(email);
			if (!userOpt.isPresent()) {
				return ResponseEntity.badRequest().body("User not found");
			}
		}
		User user = userOpt.get();
		user.setPassword(encoder.encode(newPassword));
		ur.save(user);
		return ResponseEntity.ok("Password changed successfully");
	}

}
