package com.sourabh.services;

import com.sourabh.dtos.CartDTO;
import com.sourabh.entities.CartItem;

public interface CartItemService {
	
	public CartItem createItemforCart(CartDTO cartdto);
	
}
