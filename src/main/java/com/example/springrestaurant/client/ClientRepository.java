package com.example.springrestaurant.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByFirstNameAndLastName(String first, String last);
}
