package com.foodwebsite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.foodwebsite.model.*;
import com.foodwebsite.repository.CategoryRepository;
import com.foodwebsite.repository.RestaurantRepository;
import com.foodwebsite.request.IngredientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodwebsite.repository.IngredientCategoryRepository;
import com.foodwebsite.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientsService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);

        IngredientCategory category = new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(category);
    }

    public List<String> findRestaurantIngredientNames(Long id) {
        List<IngredientsItem> items = ingredientItemRepository.findByRestaurantId(id);
        List<String> names = new ArrayList<>();
        for (IngredientsItem item : items) {
            names.add(item.getName());
        }
        return names;
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Ingredient Category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(IngredientRequest request, Long userId) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.getRestaurantId() == null) {
            throw new IllegalArgumentException("Restaurant ID cannot be null");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new Exception("Restaurant not found"));
        IngredientCategory category = ingredientCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setName(request.getName());
        ingredientsItem.setCategory(category);
        ingredientsItem.setRestaurant(restaurant);

        return ingredientItemRepository.save(ingredientsItem);
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientItem = ingredientItemRepository.findById(id);
        if (optionalIngredientItem.isEmpty()) {
            throw new Exception("Ingredient not found");
        }
        IngredientsItem ingredientsItem = optionalIngredientItem.get();
        ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
        return ingredientItemRepository.save(ingredientsItem);
    }
}