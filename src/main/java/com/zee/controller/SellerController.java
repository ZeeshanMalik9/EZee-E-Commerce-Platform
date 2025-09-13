package com.zee.controller;

import org.aspectj.weaver.patterns.IVerificationRequired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.model.VarificationCode;
import com.zee.repository.VerificationCodeRepository;
import com.zee.request.LonginRequest;
import com.zee.response.ApiResponse;
import com.zee.response.AuthResponse;
import com.zee.service.AuthService;
import com.zee.service.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
	
	private final SellerService sellerService;
	private final VerificationCodeRepository verificationRepository;
	private final AuthService authService;
	
	
//	
//	@PostMapping("/sent/login-otp")
//	public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody VarificationCode req) throws Exception{
//		
//		authService.sentLoginAndSighnupOtp(req.getEmail(),);
//		
//		ApiResponse res = new ApiResponse();
//		
//		res.setMessage("otp sent succesfully");
//		
//		return ResponseEntity.ok(res);
//	}
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginSeller(
			@RequestBody LonginRequest req) throws Exception{
		
		
		String otp = req.getOtp();
		String email = req.getEmail();
		
//		VarificationCode  verificatonCode = verificationRepository.findByEmail(email);
//		if(verificatonCode  == null || !verificatonCode.getOtp().equals(req.getOtp())) {
//			throw new Exception("Wrong Otp...");
//		}
//		
		req.setEmail("seller_"+email);
		AuthResponse authResponse = authService.signing(req);
		
		return ResponseEntity.ok(authResponse);
		
		
		
	}
	
	

}
