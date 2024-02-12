package com.sourabh.services;

import com.sourabh.controllers.ProductNotFound;
import com.sourabh.exception.CartItemNotFound;
import com.sourabh.entities.Cart;
import com.sourabh.dtos.CartDTO;


public interface CartService {
	
	public Cart addProductToCart(CartDTO cart, String token) throws CartItemNotFound;
	public Cart getCartProduct(String token);
	public Cart removeProductFromCart(CartDTO cartDto,String token) throws ProductNotFound;
//	public Cart changeQuantity(Product product,Customer customer,Integer quantity);
	
	public Cart clearCart(String token);
	
}
