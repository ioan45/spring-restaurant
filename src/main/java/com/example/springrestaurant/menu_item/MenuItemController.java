package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.menu_item.dto.AddMenuItemRequest;
import com.example.springrestaurant.menu_item.dto.IngredientEntry;
import com.example.springrestaurant.menu_item.dto.MenuItemDtoMapper;
import com.example.springrestaurant.menu_item.dto.RemoveMenuItemRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/menu-item/add")
    public ResponseEntity<String> addMenuItem(
            @RequestBody @Valid AddMenuItemRequest req
    ) {
        MenuItem item = MenuItemDtoMapper.addRequest(req);
        menuItemService.addMenuItem(item, req.getIngredients());
        return ResponseEntity.ok().body("Menu item added successfully!");
    }

    @PostMapping("/menu-item/remove")
    public ResponseEntity<String> removeMenuItem(
            @RequestBody @Valid RemoveMenuItemRequest req
    ) {
        menuItemService.removeMenuItem(req.getItemName());
        return ResponseEntity.ok().body("Menu item removed successfully!");
    }

    @GetMapping("/menu-item/get/all")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok().body(menuItemService.getAllMenuItems());
    }

    @GetMapping("/menu-item-composition/get/by/item-name")
    public ResponseEntity<List<IngredientEntry>> getMenuItemCompositionByName(
            @RequestParam String name
    ) {
        return ResponseEntity.ok().body(menuItemService.getMenuItemCompositionByName(name));
    }
}
