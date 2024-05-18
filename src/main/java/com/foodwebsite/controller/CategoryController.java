package com.foodwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.String;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.User;
import com.foodwebsite.service.CategoryService;
import com.foodwebsite.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;


    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt); // Pass the jwt token here
        Category createCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }


    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String jwt) throws Exception {

        User user = null;
        if (jwt != null) {
            user = userService.findUserByJwtToken(jwt);
        }

        List<Category> categories = categoryService.findCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
