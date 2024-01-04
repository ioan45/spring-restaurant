package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.ingredient.IngredientRepository;
import com.example.springrestaurant.menu_item.dto.IngredientEntry;
import com.example.springrestaurant.order.OrderedMenuItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private MenuItemCompositionRepository menuItemCompositionRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private OrderedMenuItemRepository orderedMenuItemRepository;

    @Test
    public void addMenuItemTest() {
        MenuItem testItem = new MenuItem(1, "TestItem", 2.5f);
        IngredientEntry entry1 = new IngredientEntry("ingr1", 2.5f);
        IngredientEntry entry2 = new IngredientEntry("ingr2", 3.5f);
        List<IngredientEntry> entries = Arrays.asList(entry1, entry2);
        when(menuItemRepository.existsByName(testItem.getName())).thenReturn(false);
        when(ingredientRepository.existsByName(entry1.getIngredientName())).thenReturn(true);
        when(ingredientRepository.existsByName(entry2.getIngredientName())).thenReturn(true);
        when(menuItemRepository.save(testItem)).thenReturn(testItem);
        when(ingredientRepository.findByName(entry1.getIngredientName())).thenReturn(null);
        when(ingredientRepository.findByName(entry2.getIngredientName())).thenReturn(null);

        menuItemService.addMenuItem(testItem, entries);

        verify(menuItemRepository, times(1)).save(testItem);
        verify(menuItemCompositionRepository, times(2)).save(any());
    }

    @Test
    public void removeMenuItemTest() {
        MenuItem testItem = new MenuItem(1, "TestItem", 2.5f);
        when(menuItemRepository.findByName(testItem.getName())).thenReturn(Optional.of(testItem));
        when(orderedMenuItemRepository.existsByMenuItem(testItem)).thenReturn(false);

        menuItemService.removeMenuItem(testItem.getName());

        verify(menuItemRepository, times(1)).delete(testItem);
        verify(menuItemCompositionRepository, times(1)).deleteAllByMenuItem(testItem);
    }

    @Test
    public void getAllMenuItemsTest() {
        MenuItem testItem1 = new MenuItem(1, "TestItem1", 2.5f);
        MenuItem testItem2 = new MenuItem(2, "TestItem2", 2.5f);
        List<MenuItem> items = Arrays.asList(testItem1, testItem2);
        when(menuItemRepository.findAll()).thenReturn(items);

        List<MenuItem> result = menuItemService.getAllMenuItems();

        assertEquals(items, result);
    }
}
