package com.example.springrestaurant.order;

import com.example.springrestaurant.menu_item.MenuItem;
import jakarta.persistence.*;

@Entity
public class OrderedMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "id_menu_item")
    private MenuItem menuItem;

    public OrderedMenuItem() {
    }

    public OrderedMenuItem(int id, int amount, OrderEntity order, MenuItem menuItem) {
        this.id = id;
        this.amount = amount;
        this.order = order;
        this.menuItem = menuItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return "OrderedMenuItem{" +
                "id=" + id +
                ", amount=" + amount +
                ", order=" + order +
                ", menuItem=" + menuItem +
                '}';
    }
}
