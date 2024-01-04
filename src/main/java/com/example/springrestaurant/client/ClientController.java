package com.example.springrestaurant.client;

import com.example.springrestaurant.client.dto.AddClientRequest;
import com.example.springrestaurant.client.dto.ClientDtoMapper;
import com.example.springrestaurant.client.dto.EditClientRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "APIs to manage clients personal information.")
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
            summary = "Add client personal information.",
            description = "Add a Client object in the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/add")
    public ResponseEntity<String> addClient(
            @RequestBody @Valid AddClientRequest newClient
    ) {
        Client client = ClientDtoMapper.addRequest(newClient);
        clientService.addNewClient(client);
        return ResponseEntity.ok().body("New client added!");
    }

    @Operation(
            summary = "Edit client personal information.",
            description = "Edit a Client object in the database (by client ID). The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/edit")
    public ResponseEntity<String> editClient(
            @RequestBody @Valid EditClientRequest editedClient
    ) {
        Client client = ClientDtoMapper.editRequest(editedClient);
        clientService.editClient(client);
        return ResponseEntity.ok().body("Client edited successfully!");
    }

    @Operation(
            summary = "Get the clients having the specified first and last name.",
            description = "Get a list of Client objects with the clients having the given name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Client.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/get/by/name")
    public ResponseEntity<List<Client>> getClientsByName(
            @RequestParam String first,
            @RequestParam String last
    ) {
        return ResponseEntity.ok().body(clientService.getClientsByName(first, last));
    }

    @Operation(
            summary = "Get client information by its ID.",
            description = "Get a Client object by specifying its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Client.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/get/by/id")
    public ResponseEntity<?> getClientById(
            @RequestParam int id
    ) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @Operation(
            summary = "Get all recorded clients.",
            description = "Get a list of Client objects with all the clients in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Client.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/get/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }
}
