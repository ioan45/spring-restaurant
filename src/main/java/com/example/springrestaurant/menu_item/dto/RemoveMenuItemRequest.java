package com.example.springrestaurant.menu_item.dto;

import jakarta.validation.constraints.NotBlank;

public class RemoveMenuItemRequest {
    @NotBlank(message = "The menu item's name shouldn't be blank!")
    private String itemName;

    public RemoveMenuItemRequest() {
    }

    public RemoveMenuItemRequest(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "RemoveMenuItemRequest{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}
