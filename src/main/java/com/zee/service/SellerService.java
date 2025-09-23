package com.zee.service;

import java.util.List;

import com.zee.dto.AccountStatus;
import com.zee.exceptions.SellerException;
import com.zee.model.Seller;

public interface SellerService {
	
	
	Seller getSellerProfile(String jwt) throws Exception;
	Seller createSeller(Seller seller) throws Exception;
	Seller getSellerById(Long id) throws SellerException;
	Seller getSellerByEmail(String email) throws Exception;
	
	List<Seller> getAllSellers(AccountStatus status);
	Seller updateSeller(Long id,Seller seller) throws Exception;
	public void deleteSeller(Long id) throws Exception;
	Seller VerifyEmail(String email, String otp) throws Exception;
	// using this admin can modify seller account
	Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception;
	

}
