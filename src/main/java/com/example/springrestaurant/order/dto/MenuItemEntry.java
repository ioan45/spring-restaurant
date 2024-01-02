package com.example.springrestaurant.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class MenuItemEntry {
    @NotBlank(message = "The menu item's name shouldn't be blank!")
    private String itemName;

    @Positive(message = "The amount ordered of the menu item should be greater than zero!")
    private int amount;

    public MenuItemEntry() {
    }

    public MenuItemEntry(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MenuItemEntry{" +
                "itemName='" + itemName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
