package com.example.springrestaurant.supplier;

import jakarta.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupplier;
    @Column(unique = true)
    private String name;
    private String email;
    private String fax;

    public Supplier() {
    }

    public Supplier(int idSupplier, String name, String email, String fax) {
        this.idSupplier = idSupplier;
        this.name = name;
        this.email = email;
        this.fax = fax;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
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
        return "Supplier{" +
                "idSupplier=" + idSupplier +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}
