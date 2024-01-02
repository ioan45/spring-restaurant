package com.example.springrestaurant.ingredient;

import com.example.springrestaurant.supplier.Supplier;
import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngredient;
    @Column(unique = true)
    private String name;
    private String unit;
    private float pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    public Ingredient() {
    }

    public Ingredient(int idIngredient, String name, String unit, float pricePerUnit, Supplier supplier) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.supplier = supplier;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", supplier=" + supplier +
                '}';
    }
}
