package com.foodwebsite.controller;


import com.foodwebsite.model.CartItem;
import com.foodwebsite.model.Order;
import com.foodwebsite.model.User;
import com.foodwebsite.request.AddCartItemRequest;
import com.foodwebsite.request.OrderRequest;
import com.foodwebsite.service.OrderService;
import com.foodwebsite.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,user);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @GetMapping("/orders/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestBody OrderRequest req,
                                             @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
      List<Order> orders = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

}
