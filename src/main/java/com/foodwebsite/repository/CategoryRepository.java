package com.foodwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodwebsite.model.Category;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByRestaurantId(Long id);
}
