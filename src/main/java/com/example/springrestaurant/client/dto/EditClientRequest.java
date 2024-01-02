package com.example.springrestaurant.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EditClientRequest {
    @Min(value = 1, message = "The client ID value can't be less than 1!")
    int idClient;

    @NotBlank(message = "First name shouldn't be blank!")
    @Size(min = 2, message = "First name should be at least 2 characters!")
    String firstName;

    @NotBlank(message = "Last name shouldn't be blank!")
    @Size(min = 2, message = "Last name should be at least 2 characters!")
    String lastName;

    @Email(message = "Invalid email address!")
    String email;

    @NotBlank(message = "Phone number shouldn't be blank!")
    String phoneNumber;

    public EditClientRequest() {
    }

    public EditClientRequest(int idClient, String firstName, String lastName, String email, String phoneNumber) {
        this.idClient = idClient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "EditClientRequest{" +
                "idClient=" + idClient +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
