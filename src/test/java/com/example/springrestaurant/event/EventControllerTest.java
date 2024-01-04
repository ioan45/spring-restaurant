package com.example.springrestaurant.event;

import com.example.springrestaurant.event.dto.AddEventRequest;
import com.example.springrestaurant.event.dto.RemoveEventRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.mockito.Mockito.*;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private EventService eventService;

    @Test
    public void addEventTest() throws Exception {
        AddEventRequest req = new AddEventRequest("Event", 25, new Date(2034, 1, 1), 1);
        mockMvc.perform(post("/event/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Event added successfully!"));

        verify(eventService, times(1)).addEvent(any(Event.class), anyInt());
    }

    @Test
    public void removeEventTest() throws Exception {
        RemoveEventRequest req = new RemoveEventRequest(new Date(2034, 1, 1), 1);

        mockMvc.perform(post("/event/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Event removed successfully!"));

        verify(eventService, times(1)).removeEvent(anyInt(), any(Date.class));
    }

    @Test
    public void getAllEventsTest() throws Exception {
        Event event = new Event(1, "Event", 25, null, null);
        List<Event> events = List.of(event);
        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/event/get/all"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(events)));

        verify(eventService, times(1)).getAllEvents();
    }
}
