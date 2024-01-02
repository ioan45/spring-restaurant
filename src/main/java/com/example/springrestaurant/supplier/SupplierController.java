package com.example.springrestaurant.supplier;

import com.example.springrestaurant.supplier.dto.AddSupplierRequest;
import com.example.springrestaurant.supplier.dto.SupplierDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSupplier(
            @RequestBody @Valid AddSupplierRequest req
    ) {
        Supplier newSupplier = SupplierDtoMapper.addRequest(req);
        supplierService.addSupplier(newSupplier);
        return ResponseEntity.ok().body("Ingredients supplier added successfully!");
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers());
    }
}
