package com.example.springrestaurant.event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public class AddEventRequest {
    @NotBlank(message = "The event name shouldn't be blank!")
    private String eventName;

    @Min(value = 10, message = "The event should have at least 10 guests!")
    @Max(value = 300, message = "The event should have at most 300 guests!")
    private int guests;

    @Future(message = "The event date should be in the future!")
    private Date date;

    @Min(value = 1, message = "The client ID value can't be less than 1!")
    private int idClient;

    public AddEventRequest() {
    }

    public AddEventRequest(String eventName, int guests, Date date, int idClient) {
        this.eventName = eventName;
        this.guests = guests;
        this.date = date;
        this.idClient = idClient;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "AddEventRequest{" +
                "eventName='" + eventName + '\'' +
                ", guests=" + guests +
                ", date=" + date +
                ", idClient=" + idClient +
                '}';
    }
}
