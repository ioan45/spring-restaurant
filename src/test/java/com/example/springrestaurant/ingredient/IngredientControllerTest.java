package com.example.springrestaurant.ingredient;

import com.example.springrestaurant.ingredient.dto.AddIngredientRequest;
import com.example.springrestaurant.ingredient.dto.RemoveIngredientRequest;
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

@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private IngredientService ingredientService;

    @Test
    public void addIngredientTest() throws Exception {
        AddIngredientRequest req = new AddIngredientRequest("name", "kg", 2.5f, "supplierName");

        mockMvc.perform(post("/ingredient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Menu item ingredient added successfully!"));

        verify(ingredientService, times(1)).addIngredient(any(Ingredient.class), anyString());
    }

    @Test
    public void removeIngredientTest() throws Exception {
        RemoveIngredientRequest req = new RemoveIngredientRequest("name");

        mockMvc.perform(post("/ingredient/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Menu item ingredient removed successfully!"));

        verify(ingredientService, times(1)).removeIngredient(anyString());
    }

    @Test
    public void getAllIngredientsTest() throws Exception {
        Ingredient ingredient = new Ingredient(1, "name", "kg", 2.5f, null);
        List<Ingredient> ingredients = List.of(ingredient);
        when(ingredientService.getAllIngredients()).thenReturn(ingredients);

        mockMvc.perform(get("/ingredient/get/all"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(ingredients)));

        verify(ingredientService, times(1)).getAllIngredients();
    }
}
