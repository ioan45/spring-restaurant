package com.example.springrestaurant.menu_item;

import com.example.springrestaurant.menu_item.dto.AddMenuItemRequest;
import com.example.springrestaurant.menu_item.dto.IngredientEntry;
import com.example.springrestaurant.menu_item.dto.RemoveMenuItemRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void addMenuItemTest() throws Exception {
        IngredientEntry entry = new IngredientEntry("name", 2.5f);
        List<IngredientEntry> entries = List.of(entry);
        AddMenuItemRequest req = new AddMenuItemRequest("name", 2.5f, entries);

        mockMvc.perform(post("/menu-item/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Menu item added successfully!"));

        verify(menuItemService, times(1)).addMenuItem(any(MenuItem.class), anyList());
    }

    @Test
    public void removeMenuItemTest() throws Exception {
        RemoveMenuItemRequest req = new RemoveMenuItemRequest("name");

        mockMvc.perform(post("/menu-item/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Menu item removed successfully!"));

        verify(menuItemService, times(1)).removeMenuItem(anyString());
    }

    @Test
    public void getMenuItemCompositionByNameTest() throws Exception {
        String menuItemName = "testName";
        IngredientEntry entry = new IngredientEntry("name", 2.5f);
        List<IngredientEntry> entries = List.of(entry);
        when(menuItemService.getMenuItemCompositionByName(menuItemName)).thenReturn(entries);

        mockMvc.perform(get("/menu-item-composition/get/by/item-name")
                        .param("name", menuItemName))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(entries)));

        verify(menuItemService, times(1)).getMenuItemCompositionByName(anyString());
    }
}
