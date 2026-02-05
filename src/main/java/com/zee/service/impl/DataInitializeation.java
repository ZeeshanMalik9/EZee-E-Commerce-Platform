package com.zee.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zee.dto.UserRole;
import com.zee.model.User;
import com.zee.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializeation implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) {
		initializeAdminUser();
	}
	
	public void initializeAdminUser() {
		String adminUsername = "lordking9990@gmail.com";
		if (userRepository.findByEmail(adminUsername)==null) {
			User adminUser = new User();
			
			adminUser.setEmail(adminUsername);
			adminUser.setPassword(passwordEncoder.encode("Zeeshan9"));
			adminUser.setFullName("Saif Ali Khan");
			adminUser.setRole(UserRole.ROLE_ADMIN);
			
			userRepository.save(adminUser);
		}
	}
	
}
