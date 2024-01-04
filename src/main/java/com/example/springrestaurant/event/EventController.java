package com.example.springrestaurant.event;

import com.example.springrestaurant.client.Client;
import com.example.springrestaurant.event.dto.AddEventRequest;
import com.example.springrestaurant.event.dto.EventDtoMapper;
import com.example.springrestaurant.event.dto.RemoveEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Event", description = "APIs to manage reservations for special events at the restaurant.")
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(
            summary = "Add a special event reservation.",
            description = "Add an Event object in the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(
            @RequestBody @Valid AddEventRequest req
    ) {
        Event event = EventDtoMapper.addRequest(req);
        eventService.addEvent(event, req.getIdClient());
        return ResponseEntity.ok().body("Event added successfully!");
    }

    @Operation(
            summary = "Remove a special event reservation.",
            description = "Remove an Event object from the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/remove")
    public ResponseEntity<String> removeEvent(
            @RequestBody @Valid RemoveEventRequest req
    ) {
        eventService.removeEvent(req.getIdClient(), req.getDate());
        return ResponseEntity.ok().body("Event removed successfully!");
    }

    @Operation(
            summary = "Get all scheduled events.",
            description = "Get a list of Event objects with all the event reservations in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Event.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/get/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

}
