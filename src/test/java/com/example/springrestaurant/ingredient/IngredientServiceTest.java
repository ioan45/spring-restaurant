package com.example.springrestaurant.ingredient;

import com.example.springrestaurant.menu_item.MenuItemCompositionRepository;
import com.example.springrestaurant.supplier.Supplier;
import com.example.springrestaurant.supplier.SupplierRepository;
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
public class IngredientServiceTest {

    @InjectMocks
    private IngredientService ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private MenuItemCompositionRepository menuItemCompositionRepository;

    @Test
    public void addIngredientTest() {
        Supplier testSupplier = new Supplier(1, "TestSupplier", "email@gmail.com", "0245.123456");
        Ingredient testIngredient = new Ingredient(1, "TestIngredient", "kg", 2.5f, testSupplier);
        when(ingredientRepository.existsByName(testIngredient.getName())).thenReturn(false);
        when(supplierRepository.findByName(testSupplier.getName())).thenReturn(Optional.of(testSupplier));
        when(ingredientRepository.save(testIngredient)).thenReturn(testIngredient);

        ingredientService.addIngredient(testIngredient, testSupplier.getName());

        verify(ingredientRepository, times(1)).save(testIngredient);
    }

    @Test
    public void removeIngredientTest() {
        Ingredient testIngredient = new Ingredient(1, "TestIngredient", "kg", 2.5f, null);
        when(ingredientRepository.existsByName(testIngredient.getName())).thenReturn(true);
        when(ingredientRepository.findByName(testIngredient.getName())).thenReturn(testIngredient);
        when(menuItemCompositionRepository.existsByIngredient(testIngredient)).thenReturn(false);

        ingredientService.removeIngredient(testIngredient.getName());

        verify(ingredientRepository, times(1)).delete(testIngredient);
    }

    @Test
    public void getAllIngredients() {
        Ingredient testIngredient1 = new Ingredient(1, "TestIngredient1", "kg", 2.5f, null);
        Ingredient testIngredient2 = new Ingredient(2, "TestIngredient2", "kg", 2.5f, null);
        List<Ingredient> ingredients = Arrays.asList(testIngredient1, testIngredient2);
        when(ingredientRepository.findAll()).thenReturn(ingredients);

        List<Ingredient> result = ingredientService.getAllIngredients();

        assertEquals(ingredients, result);
    }
}
