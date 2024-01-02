package com.example.springrestaurant.ingredient.dto;

import jakarta.validation.constraints.NotBlank;

public class RemoveIngredientRequest {
    @NotBlank(message = "The ingredient's unit of measurement shouldn't be blank!")
    private String ingredientName;

    public RemoveIngredientRequest() {
    }

    public RemoveIngredientRequest(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        return "RemoveIngredientRequest{" +
                "ingredientName='" + ingredientName + '\'' +
                '}';
    }
}
