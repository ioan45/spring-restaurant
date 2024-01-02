package com.example.springrestaurant.menu_item;

import jakarta.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItem;
    @Column(unique = true)
    private String name;
    private float price;

    public MenuItem() {
    }

    public MenuItem(int idItem, String name, float price) {
        this.idItem = idItem;
        this.name = name;
        this.price = price;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "idItem=" + idItem +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
