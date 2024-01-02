package com.example.springrestaurant.supplier.dto;

import com.example.springrestaurant.supplier.Supplier;

public class SupplierDtoMapper {
    public static Supplier addRequest(AddSupplierRequest req) {
        return new Supplier(
                0,
                req.getName(),
                req.getEmail(),
                req.getFax()
        );
    }
}
