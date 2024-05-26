package com.foodwebsite.service;

import com.foodwebsite.model.Event;
import com.foodwebsite.model.Restaurant;
import com.foodwebsite.repository.EventRepository;
import com.foodwebsite.repository.RestaurantRepository;
import com.foodwebsite.request.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Event createEvent(CreateEventRequest request, Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new Exception("Restaurant not found"));

        Event event = new Event();
        event.setImage(request.getImage());
        event.setLocation(request.getLocation());
        event.setName(request.getName());
        event.setStartedAt(request.getStartedAt());
        event.setEndsAt(request.getEndsAt());
        event.setRestaurant(restaurant);

        return eventRepository.save(event);
    }


    @Override
    public void deleteEvent(Long eventId, String jwt) throws Exception {
        Event event = getEventById(eventId);
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getEventsByRestaurant(Long restaurantId) {
        return eventRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public Event updateEvent(CreateEventRequest request, Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            event.setImage(request.getImage());
            event.setLocation(request.getLocation());
            event.setName(request.getName());
            event.setStartedAt(request.getStartedAt());
            event.setEndsAt(request.getEndsAt());
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public List<Event> findEventsByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return eventRepository.findByRestaurantId(id);
    }


}
