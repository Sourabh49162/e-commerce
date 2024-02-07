package com.sourabh.service;

import com.sourabh.models.CustomerDTO;
import com.sourabh.models.SellerDTO;
import com.sourabh.models.SessionDTO;
import com.sourabh.models.UserSession;


public interface LoginLogoutService {
	
	public UserSession loginCustomer(CustomerDTO customer);
	
	public SessionDTO logoutCustomer(SessionDTO session);
	
	public void checkTokenStatus(String token);
	
	public void deleteExpiredTokens();
	
	
	public UserSession loginSeller(SellerDTO seller);
	
	public SessionDTO logoutSeller(SessionDTO session);
	
	
}
