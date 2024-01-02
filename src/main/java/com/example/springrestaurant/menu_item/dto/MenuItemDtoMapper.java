package com.example.springrestaurant.menu_item.dto;

import com.example.springrestaurant.menu_item.MenuItem;

public class MenuItemDtoMapper {
    public static MenuItem addRequest(AddMenuItemRequest req) {
        return new MenuItem(
                0,
                req.getItemName(),
                req.getItemPrice()
        );
    }
}
