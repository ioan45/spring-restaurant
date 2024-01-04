package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.ingredient.Ingredient;
import com.example.springrestaurant.menu_item.dto.AddMenuItemRequest;
import com.example.springrestaurant.menu_item.dto.IngredientEntry;
import com.example.springrestaurant.menu_item.dto.MenuItemDtoMapper;
import com.example.springrestaurant.menu_item.dto.RemoveMenuItemRequest;
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

@Tag(name = "Menu Item", description = "APIs to manage the menu items and their composition of ingredients.")
@RestController
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @Operation(
            summary = "Add a menu item.",
            description = "Add a MenuItem object in the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/menu-item/add")
    public ResponseEntity<String> addMenuItem(
            @RequestBody @Valid AddMenuItemRequest req
    ) {
        MenuItem item = MenuItemDtoMapper.addRequest(req);
        menuItemService.addMenuItem(item, req.getIngredients());
        return ResponseEntity.ok().body("Menu item added successfully!");
    }

    @Operation(
            summary = "Remove a menu item.",
            description = "Remove a MenuItem object from the database if it isn't part of any recorded client order. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/menu-item/remove")
    public ResponseEntity<String> removeMenuItem(
            @RequestBody @Valid RemoveMenuItemRequest req
    ) {
        menuItemService.removeMenuItem(req.getItemName());
        return ResponseEntity.ok().body("Menu item removed successfully!");
    }

    @Operation(
            summary = "Get all menu items.",
            description = "Get a list of MenuItem objects with all the menu items in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = MenuItem.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/menu-item/get/all")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok().body(menuItemService.getAllMenuItems());
    }

    @Operation(
            summary = "Get the ingredients composition of the specified menu item.",
            description = "Get a list of IngredientEntry objects with the ingredients which are used to prepare the menu item specified through its name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = IngredientEntry.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/menu-item-composition/get/by/item-name")
    public ResponseEntity<List<IngredientEntry>> getMenuItemCompositionByName(
            @RequestParam String name
    ) {
        return ResponseEntity.ok().body(menuItemService.getMenuItemCompositionByName(name));
    }
}
