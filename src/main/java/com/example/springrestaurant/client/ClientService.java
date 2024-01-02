package com.example.springrestaurant.client;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addNewClient(Client client) {
        Client record = clientRepository.save(client);
        if (record.getIdClient() == 0)
            throw new RuntimeException("Error on adding the new client");
    }

    public void editClient(Client editedClient) {
        if (!clientRepository.existsById(editedClient.getIdClient()))
            throw new RuntimeException("Editing error: The client with the specified ID doesn't exist!");
        clientRepository.save(editedClient);
    }

    public List<Client> getClientsByName(String first, String last) {
        return clientRepository.findAllByFirstNameAndLastName(first, last);
    }

    public Client getClientById(int idClient) {
        return clientRepository.findById(idClient).orElseThrow(() -> new RuntimeException("There is no client with the given ID!"));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
