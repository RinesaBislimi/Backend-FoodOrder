package com.foodwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodwebsite.model.IngredientsItem;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long>{
    
    List<IngredientsItem> findByRestaurantId(Long id);
}
