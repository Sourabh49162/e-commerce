package com.sourabh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.models.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer>{

}
