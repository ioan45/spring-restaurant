package com.example.springrestaurant.event.dto;

import com.example.springrestaurant.event.Event;

public class EventDtoMapper {
    public static Event addRequest(AddEventRequest req) {
        return new Event(
                0,
                req.getEventName(),
                req.getGuests(),
                req.getDate(),
                null
        );
    }
}
