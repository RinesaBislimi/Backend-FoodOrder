package com.foodwebsite.request;

import lombok.Data;

public class UpdateCartItemRequest {
    @Data
    private Long cartItemId;

    private int quantity;


}
