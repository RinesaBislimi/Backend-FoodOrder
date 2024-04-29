package com.foodwebsite.service;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.Food;
import com.foodwebsite.model.Restaurant;
import com.foodwebsite.request.CreateFoodRequest;

import java.lang.reflect.Executable;
import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food>getRestaurantsFood(Long restaurantId,
                                        boolean isVegitarian,
                                        boolean isNonveg,
                                        boolean isSeasonal,
                                        String foodCategory
    );
    public List<Food>searchFood(String keyword);
    public Food findfoodById(Long foodId)throws Exception;

    public Food updateAvailibiityStatus(Long foodId)throws Exception;
}
