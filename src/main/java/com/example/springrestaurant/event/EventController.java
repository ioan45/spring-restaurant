package com.example.springrestaurant.event;

import com.example.springrestaurant.event.dto.AddEventRequest;
import com.example.springrestaurant.event.dto.EventDtoMapper;
import com.example.springrestaurant.event.dto.RemoveEventRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEvent(
            @RequestBody @Valid AddEventRequest req
    ) {
        Event event = EventDtoMapper.addRequest(req);
        eventService.addEvent(event, req.getIdClient());
        return ResponseEntity.ok().body("Event added successfully!");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeEvent(
            @RequestBody @Valid RemoveEventRequest req
    ) {
        eventService.removeEvent(req.getIdClient(), req.getDate());
        return ResponseEntity.ok().body("Event removed successfully!");
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

}
