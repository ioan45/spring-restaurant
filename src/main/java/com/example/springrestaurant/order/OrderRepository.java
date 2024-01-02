package com.example.springrestaurant.order;

import com.example.springrestaurant.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByClient(Client client);
}
