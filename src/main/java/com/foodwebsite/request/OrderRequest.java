package com.foodwebsite.request;


import com.foodwebsite.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restuarantId;
    private Address deliveryAddress;
}
