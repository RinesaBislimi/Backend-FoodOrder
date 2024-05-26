package com.foodwebsite.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    private String image;
    private String location;
    private String name;
    private LocalDateTime startedAt;
    private LocalDateTime endsAt;
    private Long restaurantId;
}
