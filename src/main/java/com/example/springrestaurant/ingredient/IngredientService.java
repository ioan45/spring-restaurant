package com.example.springrestaurant.ingredient;

import com.example.springrestaurant.menu_item.MenuItemCompositionRepository;
import com.example.springrestaurant.supplier.Supplier;
import com.example.springrestaurant.supplier.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final SupplierRepository supplierRepository;
    private final MenuItemCompositionRepository menuItemCompositionRepository;

    public IngredientService(IngredientRepository ingredientRepository, SupplierRepository supplierRepository, MenuItemCompositionRepository menuItemCompositionRepository) {
        this.ingredientRepository = ingredientRepository;
        this.supplierRepository = supplierRepository;
        this.menuItemCompositionRepository = menuItemCompositionRepository;
    }

    public void addIngredient(Ingredient ingredient, String supplierName) {
        if (ingredientRepository.existsByName(ingredient.getName()))
            throw new RuntimeException("There is already a menu item ingredient with the specified name!");
        Supplier supplier = supplierRepository.findByName(supplierName).orElseThrow(() -> new RuntimeException("There is no supplier with the specified name"));
        ingredient.setSupplier(supplier);
        Ingredient record = ingredientRepository.save(ingredient);
        if (record.getIdIngredient() == 0)
            throw new RuntimeException("Error on adding new menu item ingredient!");
    }

    public void removeIngredient(String ingredientName) {
        if (!ingredientRepository.existsByName(ingredientName))
            throw new RuntimeException("There is no menu item ingredient with the given name!");
        Ingredient ingredient = ingredientRepository.findByName(ingredientName);
        if (menuItemCompositionRepository.existsByIngredient(ingredient))
            throw new RuntimeException("Can't remove the ingredient because it's used by at least a menu item!");
        ingredientRepository.delete(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
