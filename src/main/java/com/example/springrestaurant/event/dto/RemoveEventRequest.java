package com.example.springrestaurant.event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public class RemoveEventRequest {
    @Future(message = "The event date should be in the future!")
    private Date date;

    @Min(value = 1, message = "The client ID value can't be less than 1!")
    private int idClient;

    public RemoveEventRequest() {
    }

    public RemoveEventRequest(Date date, int idClient) {
        this.date = date;
        this.idClient = idClient;
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
        return "RemoveEventRequest{" +
                "date=" + date +
                ", idClient=" + idClient +
                '}';
    }
}
