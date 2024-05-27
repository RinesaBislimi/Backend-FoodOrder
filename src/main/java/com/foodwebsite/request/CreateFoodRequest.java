package com.foodwebsite.request;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarin;
    private boolean seasional;
//    private List<IngredientsItem>ingredients;

}
