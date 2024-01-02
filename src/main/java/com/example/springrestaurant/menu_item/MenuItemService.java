package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.ingredient.IngredientRepository;
import com.example.springrestaurant.menu_item.dto.IngredientEntry;
import com.example.springrestaurant.order.OrderedMenuItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemCompositionRepository menuItemCompositionRepository;
    private final IngredientRepository ingredientRepository;
    private final OrderedMenuItemRepository orderedMenuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, MenuItemCompositionRepository menuItemCompositionRepository, IngredientRepository ingredientRepository, OrderedMenuItemRepository orderedMenuItemRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemCompositionRepository = menuItemCompositionRepository;
        this.ingredientRepository = ingredientRepository;
        this.orderedMenuItemRepository = orderedMenuItemRepository;
    }

    public void addMenuItem(MenuItem item, List<IngredientEntry> ingredients) {
        if (menuItemRepository.existsByName(item.getName()))
            throw new RuntimeException("There is already a menu item with the specified name!");
        for (IngredientEntry entry : ingredients)
            if (!ingredientRepository.existsByName(entry.getIngredientName()))
                throw new RuntimeException("The ingredient '" + entry.getIngredientName() + "' doesn't exist!");

        MenuItem addedItem = menuItemRepository.save(item);
        if (addedItem.getIdItem() == 0)
            throw new RuntimeException("Error on adding new menu item!");
        for (IngredientEntry ingrEntry : ingredients) {
            MenuItemComposition compEntry = new MenuItemComposition();
            compEntry.setIngredientAmountUsed(ingrEntry.getAmountUsed());
            compEntry.setMenuItem(addedItem);
            compEntry.setIngredient(ingredientRepository.findByName(ingrEntry.getIngredientName()));
            menuItemCompositionRepository.save(compEntry);
        }
    }

    @Transactional
    public void removeMenuItem(String itemName) {
        MenuItem item = menuItemRepository.findByName(itemName).orElseThrow(() -> new RuntimeException("There is no menu item with the specified name!"));
        if (orderedMenuItemRepository.existsByMenuItem(item))
            throw new RuntimeException("Can't remove the specified menu item because there are recorded orders for it!");
        menuItemCompositionRepository.deleteAllByMenuItem(item);
        menuItemRepository.delete(item);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<IngredientEntry> getMenuItemCompositionByName(String name) {
        MenuItem item = menuItemRepository.findByName(name).orElseThrow(() -> new RuntimeException("There is no menu item with the specified name!"));
        List<MenuItemComposition> composition = menuItemCompositionRepository.findAllByMenuItem(item);
        ArrayList<IngredientEntry> result = new ArrayList<>();
        for (MenuItemComposition ingredient : composition)
            result.add(new IngredientEntry(ingredient.getIngredient().getName(), ingredient.getIngredientAmountUsed()));
        return result;
    }
}
