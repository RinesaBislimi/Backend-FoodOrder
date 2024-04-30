package com.foodwebsite.service;

import com.foodwebsite.model.Cart;
import com.foodwebsite.model.CartItem;
import com.foodwebsite.request.AddCartItemRequest;
public interface CartService {

    public CartItem additemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public  Cart removeitemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart)throws Exception;

    public Cart findCartByID(Long id)throws Exception;

    public  Cart findCartByUserId(Long userId)throws Exception;


    Cart clearCart(Long userId) throws Exception;
}
