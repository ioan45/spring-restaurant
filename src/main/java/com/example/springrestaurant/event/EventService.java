package com.example.springrestaurant.event;

import com.example.springrestaurant.client.Client;
import com.example.springrestaurant.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final ClientRepository clientRepository;

    public EventService(EventRepository eventRepository, ClientRepository clientRepository) {
        this.eventRepository = eventRepository;
        this.clientRepository = clientRepository;
    }

    public void addEvent(Event newEvent, int idClient) {
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new RuntimeException("There is no client with the specified ID!"));
        newEvent.setOrganizer(client);
        Event scheduled = eventRepository.findByDate(newEvent.getDate()).orElse(null);
        if (scheduled != null)
            throw new RuntimeException("There is already an event scheduled for the given date!");
        eventRepository.save(newEvent);
    }

    public void removeEvent(int idClient, Date date) {
        Event event = eventRepository.findByDate(date).orElseThrow(() -> new RuntimeException("There is no event scheduled for the given date!"));
        if (event.getOrganizer().getIdClient() != idClient)
            throw new RuntimeException("The event scheduled at the given date is not organized by the specified client!");
        eventRepository.delete(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
