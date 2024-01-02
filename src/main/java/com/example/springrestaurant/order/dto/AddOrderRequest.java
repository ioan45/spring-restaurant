package com.example.springrestaurant.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;
import java.util.List;

public class AddOrderRequest {
    @Min(value = 1, message = "The client ID value can't be less than 1!")
    private int idClient;

    @PastOrPresent(message = "The order date should be in the past or present!")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date;

    @NotEmpty(message = "The menu items list shouldn't be empty!")
    private List<MenuItemEntry> menuItems;

    public AddOrderRequest() {
    }

    public AddOrderRequest(int idClient, Timestamp date, List<MenuItemEntry> menuItems) {
        this.idClient = idClient;
        this.date = date;
        this.menuItems = menuItems;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<MenuItemEntry> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemEntry> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "AddOrderRequest{" +
                "idClient=" + idClient +
                ", date=" + date +
                ", menuItems=" + menuItems +
                '}';
    }
}
