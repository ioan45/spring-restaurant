package com.example.springrestaurant.order;

import com.example.springrestaurant.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public OrderEntity() {
    }

    public OrderEntity(int orderId, Timestamp date, Client client) {
        this.orderId = orderId;
        this.date = date;
        this.client = client;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", client=" + client +
                '}';
    }
}
