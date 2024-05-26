package com.foodwebsite.repository;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findByRestaurantId(Long id);

}
