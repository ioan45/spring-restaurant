package com.example.springrestaurant.client;

import com.example.springrestaurant.client.dto.AddClientRequest;
import com.example.springrestaurant.client.dto.EditClientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.mockito.Mockito.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ClientService clientService;

    @Test
    public void addClientTest() throws Exception {
        AddClientRequest req = new AddClientRequest("FirstName", "LastName", "email@gmail.com", "0712345678");

        mockMvc.perform(post("/client/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("New client added!"));

        verify(clientService, times(1)).addNewClient(any(Client.class));
    }

    @Test
    public void editClientTest() throws Exception {
        EditClientRequest req = new EditClientRequest(1, "FirstName", "LastName", "email@gmail.com", "0712345678");

        mockMvc.perform(post("/client/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Client edited successfully!"));

        verify(clientService, times(1)).editClient(any(Client.class));
    }

    @Test
    public void getClientByNameTest() throws Exception {
        String firstName = "FirstName";
        String lastName = "LastName";
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        List<Client> clients = List.of(testClient);
        when(clientService.getClientsByName(firstName, lastName)).thenReturn(clients);

        mockMvc.perform(get("/client/get/by/name")
                        .param("first", firstName)
                        .param("last", lastName))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(clients)));

        verify(clientService, times(1)).getClientsByName(firstName, lastName);
    }

    @Test
    public void getClientByIdTest() throws Exception {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        when(clientService.getClientById(1)).thenReturn(testClient);

        mockMvc.perform(get("/client/get/by/id")
                        .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(testClient)));

        verify(clientService, times(1)).getClientById(1);
    }

    @Test
    public void getAllClients() throws Exception {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        List<Client> clients = List.of(testClient);
        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/client/get/all"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(clients)));

        verify(clientService, times(1)).getAllClients();
    }
}
