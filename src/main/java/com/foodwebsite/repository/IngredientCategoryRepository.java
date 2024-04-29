package com.foodwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodwebsite.model.IngredientCategory;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{
    
    List<IngredientCategory> findByRestaurantId(Long id);
}
