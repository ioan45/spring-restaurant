package com.example.springrestaurant.ingredient.dto;

import jakarta.validation.constraints.*;

public class AddIngredientRequest {
    @NotBlank(message = "The ingredient name shouldn't be blank!")
    private String ingredientName;

    @NotBlank(message = "The ingredient's unit of measurement shouldn't be blank!")
    private String unit;

    @Positive(message = "The ingredient's price should be greater than zero!")
    private float pricePerUnit;

    @NotBlank(message = "The supplier name shouldn't be blank!")
    private String supplierName;

    public AddIngredientRequest() {
    }

    public AddIngredientRequest(String ingredientName, String unit, float pricePerUnit, String supplierName) {
        this.ingredientName = ingredientName;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.supplierName = supplierName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "AddIngredientRequest{" +
                "ingredientName='" + ingredientName + '\'' +
                ", unit='" + unit + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
