package com.example.springrestaurant.event;

import com.example.springrestaurant.client.Client;
import com.example.springrestaurant.client.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void addEventTest() {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        Event testEvent = new Event(1, "TestEvent", 10, new Date(2024, 1, 1), testClient);
        when(clientRepository.findById(testClient.getIdClient())).thenReturn(Optional.of(testClient));
        when(eventRepository.findByDate(testEvent.getDate())).thenReturn(Optional.empty());
        when(eventRepository.save(testEvent)).thenReturn(testEvent);

        eventService.addEvent(testEvent, testClient.getIdClient());

        verify(clientRepository, times(1)).findById(testClient.getIdClient());
        verify(eventRepository, times(1)).findByDate(testEvent.getDate());
        verify(eventRepository, times(1)).save(testEvent);
    }

    @Test
    public void removeEventTest() {
        Date testDate = new Date(2024, 1, 1);
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        Event testEvent = new Event(1, "TestEvent", 10, testDate, testClient);
        when(eventRepository.findByDate(testDate)).thenReturn(Optional.of(testEvent));

        eventService.removeEvent(testClient.getIdClient(), testDate);

        verify(eventRepository, times(1)).findByDate(testDate);
        verify(eventRepository, times(1)).delete(testEvent);
    }

    @Test
    public void getAllEvents() {
        Event testEvent1 = new Event(1, "TestEvent1", 10, new Date(2024, 1, 1), null);
        Event testEvent2 = new Event(2, "TestEvent2", 23, new Date(2024, 1, 1), null);
        List<Event> events = Arrays.asList(testEvent1, testEvent2);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertEquals(events, result);
    }
}
