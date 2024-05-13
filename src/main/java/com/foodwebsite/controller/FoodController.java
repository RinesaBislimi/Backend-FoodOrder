package com.foodwebsite.controller;


import com.foodwebsite.model.Food;
import com.foodwebsite.model.User;
import com.foodwebsite.service.FoodService;
import com.foodwebsite.service.RestaurantService;
import com.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                 @RequestHeader("Authorizaton") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED)                ;
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> searchFood(@RequestParam (required = false) boolean vagetarian,
                                                 @RequestParam  (required = false) boolean seasonal,
                                                 @RequestParam (required = false) boolean nonveg,
                                                 @RequestParam(required = false) String food_category,
                                                 @PathVariable Long restaurantId,
                                                 @RequestHeader("Authorizaton") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.getRestaurantsFood(restaurantId,vagetarian,nonveg,seasonal,food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
