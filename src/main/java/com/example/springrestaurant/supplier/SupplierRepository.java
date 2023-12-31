package com.example.springrestaurant.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    boolean existsByName(String name);
    Optional<Supplier> findByName(String name);
}
