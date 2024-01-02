package com.example.springrestaurant.menu_item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class IngredientEntry {
    @NotBlank(message = "The menu item's name shouldn't be blank!")
    private String ingredientName;

    @Positive(message = "The menu item's price should be greater than zero!")
    private float amountUsed;

    public IngredientEntry() {
    }

    public IngredientEntry(String ingredientName, float amountUsed) {
        this.ingredientName = ingredientName;
        this.amountUsed = amountUsed;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public float getAmountUsed() {
        return amountUsed;
    }

    public void setAmountUsed(float amountUsed) {
        this.amountUsed = amountUsed;
    }

    @Override
    public String toString() {
        return "IngredientEntry{" +
                "ingredientName='" + ingredientName + '\'' +
                ", amountUsed=" + amountUsed +
                '}';
    }
}
