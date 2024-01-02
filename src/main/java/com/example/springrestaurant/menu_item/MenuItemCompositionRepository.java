package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemCompositionRepository extends JpaRepository<MenuItemComposition, Integer> {
    List<MenuItemComposition> findAllByMenuItem(MenuItem item);
    void deleteAllByMenuItem(MenuItem item);
    boolean existsByIngredient(Ingredient ingredient);
}
