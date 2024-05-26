package com.foodwebsite.controller;

import com.foodwebsite.model.Category;
import com.foodwebsite.model.Event;
import com.foodwebsite.model.User;
import com.foodwebsite.request.CreateEventRequest;
import com.foodwebsite.service.EventService;
import com.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest request,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Event event = eventService.createEvent(request, user.getId());
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Event>> getRestaurantEvent(
            @PathVariable Long id,
            @RequestHeader( "Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Event> events = eventService.findEventsByRestaurantId(id);
        return new ResponseEntity<>(events, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@RequestBody CreateEventRequest request,
                                             @PathVariable Long eventId) {
        Event updatedEvent = eventService.updateEvent(request, eventId);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        try {
            eventService.deleteEvent(eventId, null); // Assuming JWT is not needed for deletion
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
