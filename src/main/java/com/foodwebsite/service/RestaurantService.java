package com.foodwebsite.service;

import com.foodwebsite.dto.RestaurantDto;
import com.foodwebsite.model.Restaurant;
import com.foodwebsite.model.User;
import com.foodwebsite.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req , User user);


    public Restaurant updateRestaurant(Long restaurantId , CreateRestaurantRequest updateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long Id ) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;


}
