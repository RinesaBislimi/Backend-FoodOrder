package com.foodwebsite.controller;

import com.foodwebsite.model.Cart;
import com.foodwebsite.model.CartItem;
import com.foodwebsite.request.AddCartItemRequest;
import com.foodwebsite.request.UpdateCartItemRequest;
import com.foodwebsite.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api")
public class CartController {

    @Autowired
    private CartService cartService;
    @PutMapping("/cart/add")

    public ResponseEntity<CartItem>addItemToCart(@RequestBody AddCartItemRequest req,
                                                 @RequestHeader("Authorization")String jwt){
        CartItem cartItem=cartService.addItemToCart(req,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);


    }
    @PutMapping("/cart-item/update")

    public ResponseEntity<CartItem>updateCartItemQuantity
            (@RequestBody UpdateCartItemRequest req,
                                                 @RequestHeader("Authorization")String jwt){
        CartItem cartItem=cartService.updateCartItemQuantity(req.getCartItemId(),req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
}
    @DeleteMapping("/cart-item/{id}/remove")

    public ResponseEntity<Cart>removeCartItem(
            @PathVariable Long id,
             @RequestHeader("Authorization")String jwt){
        Cart car=cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")

    public ResponseEntity<Cart>clearCart(

             @RequestHeader("Authorization")String jwt){
        Cart cart=cartService.clearCart(jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")

    public ResponseEntity<Cart>findUserCart(

            @RequestHeader("Authorization")String jwt){
        Cart cart=cartService.findCartByUserId(jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }