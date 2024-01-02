package com.example.springrestaurant.menu_item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    boolean existsByName(String name);
    Optional<MenuItem> findByName(String name);
}
