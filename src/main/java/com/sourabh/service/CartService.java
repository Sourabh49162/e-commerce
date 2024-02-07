package com.sourabh.service;

import com.sourabh.controller.ProductNotFound;
import com.sourabh.exception.CartItemNotFound;
import com.sourabh.models.Cart;
import com.sourabh.models.CartDTO;


public interface CartService {
	
	public Cart addProductToCart(CartDTO cart, String token) throws CartItemNotFound;
	public Cart getCartProduct(String token);
	public Cart removeProductFromCart(CartDTO cartDto,String token) throws ProductNotFound;
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);
	
	public Cart clearCart(String token);
	
}
