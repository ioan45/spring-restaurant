package com.example.springrestaurant.ingredient;

import com.example.springrestaurant.ingredient.dto.AddIngredientRequest;
import com.example.springrestaurant.ingredient.dto.IngredientDtoMapper;
import com.example.springrestaurant.ingredient.dto.RemoveIngredientRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIngredient(
            @RequestBody @Valid AddIngredientRequest req
    ) {
        Ingredient ingredient = IngredientDtoMapper.addRequest(req);
        ingredientService.addIngredient(ingredient, req.getSupplierName());
        return ResponseEntity.ok().body("Menu item ingredient added successfully!");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeIngredient(
            @RequestBody @Valid RemoveIngredientRequest req
    ) {
        ingredientService.removeIngredient(req.getIngredientName());
        return ResponseEntity.ok().body("Menu item ingredient removed successfully!");
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok().body(ingredientService.getAllIngredients());
    }
}
