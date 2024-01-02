package com.example.springrestaurant.menu_item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class AddMenuItemRequest {
    @NotBlank(message = "The menu item's name shouldn't be blank!")
    private String itemName;

    @Positive(message = "The menu item's price should be greater than zero!")
    private float itemPrice;

    @NotEmpty(message = "The ingredients list shouldn't be empty!")
    private List<IngredientEntry> ingredients;

    public AddMenuItemRequest() {
    }

    public AddMenuItemRequest(String itemName, float itemPrice, List<IngredientEntry> ingredients) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.ingredients = ingredients;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public List<IngredientEntry> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntry> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "AddMenuItemRequest{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", ingredients=" + ingredients +
                '}';
    }
}
