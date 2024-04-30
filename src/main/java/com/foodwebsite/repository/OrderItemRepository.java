package com.foodwebsite.repository;

import com.foodwebsite.model.Order;
import com.foodwebsite.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


}
