package com.example.springrestaurant.event;

import com.example.springrestaurant.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findByDate(Date date);
}
