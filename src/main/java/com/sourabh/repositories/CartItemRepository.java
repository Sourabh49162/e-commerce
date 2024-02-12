package com.sourabh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

}
