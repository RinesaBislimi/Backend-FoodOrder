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
    private IngredientsService ingredientsServicel;
    
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createCategory(@RequestBody IngredientCategoryRequest request,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(request.getName(), user.getId());

        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }





    @PostMapping("/item")
    public ResponseEntity<IngredientsItem> createIngredientItem(
        @RequestBody IngredientRequest req
        ) throws Exception {

        IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
        @PathVariable Long id
        ) throws Exception {

        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
        @PathVariable Long id
        ) throws Exception {

        List<IngredientsItem> items = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
        @PathVariable Long id
        ) throws Exception {

        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
//    @PostMapping("/item")
//    public ResponseEntity<IngredientsItem> createIngredient(@RequestBody IngredientRequest request,
//                                                            @RequestHeader("Authorization") String jwt) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
//        IngredientsItem ingredient = ingredientsService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
//        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
//    }
}
