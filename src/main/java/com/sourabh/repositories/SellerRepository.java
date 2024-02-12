package com.sourabh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
	
	Optional<Seller> findByMobile(String mobile);
	
}
