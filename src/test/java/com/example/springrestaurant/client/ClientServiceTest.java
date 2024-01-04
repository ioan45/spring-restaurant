package com.example.springrestaurant.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void addNewClientTest() {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        when(clientRepository.save(testClient)).thenReturn(testClient);

        clientService.addNewClient(testClient);

        verify(clientRepository, times(1)).save(testClient);
    }

    @Test
    public void editClientTest() {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        when(clientRepository.existsById(testClient.getIdClient())).thenReturn(true);
        when(clientRepository.save(testClient)).thenReturn(testClient);

        clientService.editClient(testClient);

        verify(clientRepository, times(1)).existsById(testClient.getIdClient());
        verify(clientRepository, times(1)).save(testClient);
    }

    @Test
    public void getClientsByNameTest() {
        Client testClient1 = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        Client testClient2 = new Client(2, "FirstName", "LastName", "email@gmail.com", "0712345678");
        List<Client> clients = Arrays.asList(testClient1, testClient2);
        when(clientRepository.findAllByFirstNameAndLastName("FirstName", "LastName")).thenReturn(clients);

        List<Client> result = clientService.getClientsByName("FirstName", "LastName");

        assertEquals(clients, result);
    }

    @Test
    public void getClientByIdTest() {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        when(clientRepository.findById(testClient.getIdClient())).thenReturn(Optional.of(testClient));

        Client result = clientService.getClientById(testClient.getIdClient());

        assertEquals(testClient, result);
    }

    @Test
    public void getAllClientsTest() {
        Client testClient1 = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        Client testClient2 = new Client(2, "FirstName", "LastName", "email@gmail.com", "0712345678");
        List<Client> clients = Arrays.asList(testClient1, testClient2);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();

        assertEquals(clients, result);
    }
}
