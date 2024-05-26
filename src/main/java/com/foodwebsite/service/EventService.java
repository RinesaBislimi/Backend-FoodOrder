package com.foodwebsite.service;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.Event;
import com.foodwebsite.request.CreateEventRequest;

import java.util.List;

public interface EventService {
    Event createEvent(CreateEventRequest req, Long userId)throws Exception;
    void deleteEvent(Long eventId, String jwt) throws Exception;
    List<Event> getEventsByRestaurant(Long restaurantId);
    Event getEventById(Long eventId);
    Event updateEvent(CreateEventRequest request, Long eventId);
    public List<Event> findEventsByRestaurantId(Long id) throws Exception;
}
