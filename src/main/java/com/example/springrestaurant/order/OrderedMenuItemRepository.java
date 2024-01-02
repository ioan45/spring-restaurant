package com.example.springrestaurant.order;

import com.example.springrestaurant.menu_item.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedMenuItemRepository extends JpaRepository<OrderedMenuItem, Integer> {
    List<OrderedMenuItem> findAllByOrder(OrderEntity order);
    void deleteAllByOrder(OrderEntity order);
    boolean existsByMenuItem(MenuItem item);
}
