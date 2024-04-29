package com.foodwebsite.controller;

import com.foodwebsite.dto.RestaurantDto;
import com.foodwebsite.model.Restaurant;
import com.foodwebsite.model.User;
import com.foodwebsite.request.CreateRestaurantRequest;
import com.foodwebsite.service.RestaurantService;
import com.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{

        User user=userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant=restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);


    }
    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt

    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant=restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);


    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantByIf(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id

    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);


    }
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }




}
