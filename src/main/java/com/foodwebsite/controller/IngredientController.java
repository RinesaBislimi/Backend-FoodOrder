package com.foodwebsite.controller;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.User;
import com.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodwebsite.model.IngredientCategory;
import com.foodwebsite.model.IngredientsItem;
import com.foodwebsite.request.IngredientCategoryRequest;
import com.foodwebsite.request.IngredientRequest;
import com.foodwebsite.service.IngredientsService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createCategory(@RequestBody IngredientCategoryRequest request,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(request.getName(), user.getId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req,
                                                                @RequestHeader("Authorization") String jwt) throws Exception {
        if (jwt == null || jwt.isEmpty()) {
            throw new IllegalArgumentException("JWT token is missing");
        }
        User user = userService.findUserByJwtToken(jwt);
        if (user == null) {
            throw new Exception("User not found or invalid JWT token");
        }

        // Log request data
        System.out.println("Request Data: " + req);

        // Ensure the request is properly structured
        if (req.getName() == null || req.getCategoryId() == null || req.getRestaurantId() == null) {
            throw new IllegalArgumentException("Invalid request payload");
        }

        IngredientsItem item = ingredientsService.createIngredientItem(req, user.getId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/names")
    public ResponseEntity<List<String>> getRestaurantIngredientNames(@PathVariable Long id) throws Exception {
        List<String> names = ingredientsService.findRestaurantIngredientNames(id);
        return new ResponseEntity<>(names, HttpStatus.OK);
    }


    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}