package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.ingredient.Ingredient;
import jakarta.persistence.*;

@Entity
public class MenuItemComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float ingredientAmountUsed;

    @ManyToOne
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "id_menu_item")
    private MenuItem menuItem;

    public MenuItemComposition() {
    }

    public MenuItemComposition(int id, float ingredientAmountUsed, Ingredient ingredient, MenuItem menuItem) {
        this.id = id;
        this.ingredientAmountUsed = ingredientAmountUsed;
        this.ingredient = ingredient;
        this.menuItem = menuItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIngredientAmountUsed() {
        return ingredientAmountUsed;
    }

    public void setIngredientAmountUsed(float ingredientAmountUsed) {
        this.ingredientAmountUsed = ingredientAmountUsed;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return "MenuItemComposition{" +
                "id=" + id +
                ", ingredientAmountUsed=" + ingredientAmountUsed +
                ", ingredient=" + ingredient +
                ", menuItem=" + menuItem +
                '}';
    }
}
