package com.sourabh.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sourabh.services.LoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.exception.CustomerNotFoundException;
import com.sourabh.exception.LoginException;
import com.sourabh.exception.SellerNotFoundException;
import com.sourabh.entities.Customer;
import com.sourabh.dtos.CustomerDTO;
import com.sourabh.entities.Seller;
import com.sourabh.dtos.SellerDTO;
import com.sourabh.dtos.SessionDTO;
import com.sourabh.entities.UserSession;
import com.sourabh.repositories.CustomerRepository;
import com.sourabh.repositories.SellerRepository;
import com.sourabh.repositories.SessionRepository;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService {

	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SellerRepository sellerRepository;

 
	
	// Method to login a customer

	@Override
	public UserSession loginCustomer(CustomerDTO loginCustomer) {
		
		Optional<Customer> res = customerRepository.findByMobileNo(loginCustomer.getMobileId());
		
		if(res.isEmpty())
			throw new CustomerNotFoundException("Customer record does not exist with given mobile number");
		
		Customer existingCustomer = res.get();
		
		Optional<UserSession> opt = sessionRepository.findByUserId(existingCustomer.getCustomerId());
		
		if(opt.isPresent()) {
			
			UserSession user = opt.get();
			
			if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
				sessionRepository.delete(user);
			}
			else
				throw new LoginException("User already logged in");
			
		}
		
		
		if(existingCustomer.getPassword().equals(loginCustomer.getPassword())) {
		
			UserSession newSession = new UserSession();
			
			newSession.setUserId(existingCustomer.getCustomerId());
			newSession.setUserType("customer");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String token = "customer_" + uuid.toString().split("-")[0];
			
			newSession.setToken(token);
			
			return sessionRepository.save(newSession);
		}
		else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	
	// Method to logout a customer
	
	@Override
	public SessionDTO logoutCustomer(SessionDTO sessionToken) {
		
		String token = sessionToken.getToken();
		
		checkTokenStatus(token);
		
		Optional<UserSession> opt = sessionRepository.findByToken(token);
		
		if(!opt.isPresent())
			throw new LoginException("User not logged in. Invalid session token. Login Again.");
		
		UserSession session = opt.get();
		
		sessionRepository.delete(session);
		
		sessionToken.setMessage("Logged out sucessfully.");
		
		return sessionToken;
	}
	
	
	
	// Method to check status of session token
	
	
	@Override
	public void checkTokenStatus(String token) {
		
		Optional<UserSession> opt = sessionRepository.findByToken(token);
		
		if(opt.isPresent()) {
			UserSession session = opt.get();
			LocalDateTime endTime = session.getSessionEndTime();
			boolean flag = false;
			if(endTime.isBefore(LocalDateTime.now())) {
				sessionRepository.delete(session);
				flag = true;
			}
			
			deleteExpiredTokens();
			if(flag)
				throw new LoginException("Session expired. Login Again");
		}
		else {
			throw new LoginException("User not logged in. Invalid session token. Please login first.");
		}
		
	}

	
	// Method to login a valid seller and generate a seller token
	
	@Override
	public UserSession loginSeller(SellerDTO seller) {
		
		Optional<Seller> res = sellerRepository.findByMobile(seller.getMobile());
		
		if(res.isEmpty())
			throw new SellerNotFoundException("Seller record does not exist with given mobile number");
		
		Seller existingSeller = res.get();
		
		Optional<UserSession> opt = sessionRepository.findByUserId(existingSeller.getSellerId());
		
		if(opt.isPresent()) {
			
			UserSession user = opt.get();
			
			if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
				sessionRepository.delete(user);
			}
			else
				throw new LoginException("User already logged in");
			
		}
		
		
		if(existingSeller.getPassword().equals(seller.getPassword())) {
		
			UserSession newSession = new UserSession();
			
			newSession.setUserId(existingSeller.getSellerId());
			newSession.setUserType("seller");
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String token = "seller_" + uuid.toString().split("-")[0];
			
			newSession.setToken(token);
			
			return sessionRepository.save(newSession);
		}
		else {
			throw new LoginException("Password Incorrect. Try again.");
		}
	}

	
	// Method to logout a seller and delete his session token
	
	@Override
	public SessionDTO logoutSeller(SessionDTO session) {
		
		String token = session.getToken();
		
		checkTokenStatus(token);
		
		Optional<UserSession> opt = sessionRepository.findByToken(token);
		
		if(!opt.isPresent())
			throw new LoginException("User not logged in. Invalid session token. Login Again.");
		
		UserSession user = opt.get();
		
		sessionRepository.delete(user);
		
		session.setMessage("Logged out sucessfully.");
		
		return session;
	}
	
	
	// Method to delete expired tokens
	
	@Override
	public void deleteExpiredTokens() {
		
		System.out.println("Inside delete tokens");
		
		List<UserSession> users = sessionRepository.findAll();
		
		System.out.println(users);
		
		if(users.size() > 0) {
			for(UserSession user:users) {
				System.out.println(user.getUserId());
				LocalDateTime endTime = user.getSessionEndTime();
				if(endTime.isBefore(LocalDateTime.now())) {
					System.out.println(user.getUserId());
					sessionRepository.delete(user);
				}
			}
		}
	}
	
}
