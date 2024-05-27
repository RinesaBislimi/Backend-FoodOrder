package com.foodwebsite.controller;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.Food;
import com.foodwebsite.model.Restaurant;
import com.foodwebsite.model.User;
import com.foodwebsite.request.CreateFoodRequest;
import com.foodwebsite.response.MessageResponse;
import com.foodwebsite.service.FoodService;
import com.foodwebsite.service.RestaurantService;
import com.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Food>> getMenuForRestaurant(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) {
        try {
            // Gjeni user-in bazuar në JWT token
            User user = userService.findUserByJwtToken(jwt);

            // Merrni listën e ushqimeve për restorantin me ID e caktuar
            List<Food> food = foodService.findMenuByRestaurantId(id);

            // Kthejeni listën e ushqimeve si përgjigje me statusin HTTP OK
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (Exception e) {
            // Në rast se ndodh ndonjë gabim, kthe një përgjigje me statusin HTTP ERROR 500
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) {
        try {
            User user = userService.findUserByJwtToken(jwt);
            Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
            Food food = foodService.createFood(req,user.getId());
            return new ResponseEntity<>(food, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Food deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // API endpoint for updating the availability status of a food item
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailibiityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }





}
