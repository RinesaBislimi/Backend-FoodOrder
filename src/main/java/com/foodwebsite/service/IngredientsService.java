package com.foodwebsite.service;

import com.foodwebsite.model.IngredientCategory;
import com.foodwebsite.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaruantId);

    public IngredientsItem updateStock(Long id) throws Exception;
}
