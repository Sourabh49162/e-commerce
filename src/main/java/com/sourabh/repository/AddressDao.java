package com.sourabh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourabh.models.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{

}
