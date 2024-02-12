package com.sourabh.services;

import com.sourabh.dtos.CustomerDTO;
import com.sourabh.dtos.SellerDTO;
import com.sourabh.dtos.SessionDTO;
import com.sourabh.entities.UserSession;


public interface LoginLogoutService {
	
	public UserSession loginCustomer(CustomerDTO customer);
	
	public SessionDTO logoutCustomer(SessionDTO session);
	
	public void checkTokenStatus(String token);
	
	public void deleteExpiredTokens();
	
	
	public UserSession loginSeller(SellerDTO seller);
	
	public SessionDTO logoutSeller(SessionDTO session);
	
	
}
