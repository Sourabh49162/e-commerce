package com.sourabh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourabh.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
