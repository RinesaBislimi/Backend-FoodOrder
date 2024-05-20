package com.foodwebsite.controller;


import com.foodwebsite.model.Category;
import com.foodwebsite.model.Food;
import com.foodwebsite.model.Restaurant;
import com.foodwebsite.model.User;
import com.foodwebsite.request.CreateFoodRequest;
import com.foodwebsite.service.CategoryService;
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
    private CategoryService categoryService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String jwt) {
        try {
            // Retrieve user information based on the JWT token
            User user = userService.findUserByJwtToken(jwt);

            // Retrieve category by its ID
            Category category = categoryService.findCategoryById(request.getCategory().getId());

            // Retrieve restaurant by its ID
            Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

            // Assuming foodService has a method to create a new food item
            Food createdFood = foodService.createFood(request, category, restaurant);

            return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                 @RequestHeader("Authorizaton") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods=foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED)                ;
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam(required = false) boolean vegetarian,
            @RequestParam(required = false) boolean seasonal,
            @RequestParam(required = false) boolean nonveg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) {
        try {
            // Assuming you have implemented the logic to retrieve food items based on the provided parameters
            List<Food> foods = foodService.getRestaurantsFood(
                    restaurantId, vegetarian, nonveg, seasonal, food_category);
            return new ResponseEntity<>(foods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
