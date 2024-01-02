package com.example.springrestaurant.ingredient.dto;

import com.example.springrestaurant.ingredient.Ingredient;

public class IngredientDtoMapper {
    public static Ingredient addRequest(AddIngredientRequest req) {
        return new Ingredient(
                0,
                req.getIngredientName(),
                req.getUnit(),
                req.getPricePerUnit(),
                null
        );
    }
}
