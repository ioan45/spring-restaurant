package com.example.springrestaurant.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddSupplierRequest {
    @NotBlank(message = "Supplier name shouldn't be blank!")
    @Size(min = 3, message = "Supplier name should be at least 3 characters!")
    String name;

    @Email(message = "Invalid email address!")
    String email;

    @NotBlank(message = "Fax number shouldn't be blank!")
    String fax;

    public AddSupplierRequest() {
    }

    public AddSupplierRequest(String name, String email, String fax) {
        this.name = name;
        this.email = email;
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "AddSupplierRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}
