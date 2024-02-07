package com.sourabh.service;

import com.sourabh.models.CartDTO;
import com.sourabh.models.CartItem;

public interface CartItemService {
	
	public CartItem createItemforCart(CartDTO cartdto);
	
}
