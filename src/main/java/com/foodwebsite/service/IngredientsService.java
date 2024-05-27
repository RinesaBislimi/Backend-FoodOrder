package com.foodwebsite.service;

import com.foodwebsite.model.IngredientCategory;
import com.foodwebsite.model.IngredientsItem;
import com.foodwebsite.request.IngredientRequest;

import java.util.List;

public interface IngredientsService {
    
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(IngredientRequest request, Long userId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaruantId);

    public IngredientsItem updateStock(Long id) throws Exception;
    public List<String> findRestaurantIngredientNames(Long id);
}
