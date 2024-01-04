package com.example.springrestaurant.ingredient;

import com.example.springrestaurant.event.Event;
import com.example.springrestaurant.ingredient.dto.AddIngredientRequest;
import com.example.springrestaurant.ingredient.dto.IngredientDtoMapper;
import com.example.springrestaurant.ingredient.dto.RemoveIngredientRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ingredient", description = "APIs to manage ingredients used to prepare the menu items.")
@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(
            summary = "Add a menu item ingredient.",
            description = "Add an Ingredient object in the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/add")
    public ResponseEntity<String> addIngredient(
            @RequestBody @Valid AddIngredientRequest req
    ) {
        Ingredient ingredient = IngredientDtoMapper.addRequest(req);
        ingredientService.addIngredient(ingredient, req.getSupplierName());
        return ResponseEntity.ok().body("Menu item ingredient added successfully!");
    }

    @Operation(
            summary = "Remove a menu item ingredient.",
            description = "Remove an Ingredient object from the database if it isn't used by any menu items. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/remove")
    public ResponseEntity<String> removeIngredient(
            @RequestBody @Valid RemoveIngredientRequest req
    ) {
        ingredientService.removeIngredient(req.getIngredientName());
        return ResponseEntity.ok().body("Menu item ingredient removed successfully!");
    }

    @Operation(
            summary = "Get all menu item ingredients.",
            description = "Get a list of Ingredient objects with all the menu item ingredients in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/get/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ResponseEntity.ok().body(ingredientService.getAllIngredients());
    }
}
