package com.foodwebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private IngredientCategory category;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private boolean inStoke = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setCategory(IngredientCategory ingredientCategory) {
        this.category = ingredientCategory;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isInStoke() {
        return inStoke;
    }

    public void setInStoke(boolean inStoke) {
        this.inStoke = inStoke;
    }
}