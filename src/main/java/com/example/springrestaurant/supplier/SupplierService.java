package com.example.springrestaurant.supplier;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void addSupplier(Supplier supplier) {
        if (supplierRepository.existsByName(supplier.getName()))
            throw new RuntimeException("There is already a supplier with the specified name!");
        Supplier record = supplierRepository.save(supplier);
        if (record.getIdSupplier() == 0)
            throw new RuntimeException("Error on adding new ingredients supplier!");
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
