package com.example.springrestaurant.client.dto;

import com.example.springrestaurant.client.Client;
import com.example.springrestaurant.client.dto.AddClientRequest;

import java.util.ArrayList;

public class ClientDtoMapper {
    public static Client addRequest(AddClientRequest req) {
        return new Client(
                0,
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                req.getPhoneNumber()
        );
    }

    public static Client editRequest(EditClientRequest req) {
        return new Client(
                req.getIdClient(),
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                req.getPhoneNumber()
        );
    }
}
