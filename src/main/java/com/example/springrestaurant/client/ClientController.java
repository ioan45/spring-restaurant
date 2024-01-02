package com.example.springrestaurant.client;

import com.example.springrestaurant.client.dto.AddClientRequest;
import com.example.springrestaurant.client.dto.ClientDtoMapper;
import com.example.springrestaurant.client.dto.EditClientRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addClient(
            @RequestBody @Valid AddClientRequest newClient
    ) {
        Client client = ClientDtoMapper.addRequest(newClient);
        clientService.addNewClient(client);
        return ResponseEntity.ok().body("New client added!");
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editClient(
            @RequestBody @Valid EditClientRequest editedClient
    ) {
        Client client = ClientDtoMapper.editRequest(editedClient);
        clientService.editClient(client);
        return ResponseEntity.ok().body("Client edited successfully!");
    }

    @GetMapping("/get/by/name")
    public ResponseEntity<List<Client>> getClientsByName(
            @RequestParam String first,
            @RequestParam String last
    ) {
        return ResponseEntity.ok().body(clientService.getClientsByName(first, last));
    }

    @GetMapping("/get/by/id")
    public ResponseEntity<?> getClientById(
            @RequestParam int id
    ) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }
}
