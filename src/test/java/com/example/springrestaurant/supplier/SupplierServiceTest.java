package com.example.springrestaurant.supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @Test
    public void addSupplierTest() {
        Supplier testSupplier = new Supplier(1, "TestSupplier", "email@gmail.com", "0245.123456");
        when(supplierRepository.existsByName(testSupplier.getName())).thenReturn(false);
        when(supplierRepository.save(testSupplier)).thenReturn(testSupplier);

        supplierService.addSupplier(testSupplier);

        verify(supplierRepository, times(1)).save(testSupplier);
    }

    @Test
    public void getAllSuppliersTest() {
        Supplier testSupplier1 = new Supplier(1, "TestSupplier1", "email@gmail.com", "0245.123456");
        Supplier testSupplier2 = new Supplier(2, "TestSupplier2", "email@gmail.com", "0245.123456");
        List<Supplier> suppliers = Arrays.asList(testSupplier1, testSupplier2);
        when(supplierRepository.findAll()).thenReturn(suppliers);

        List<Supplier> result = supplierService.getAllSuppliers();

        assertEquals(suppliers, result);
    }
}
