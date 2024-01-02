package com.example.springrestaurant.order.dto;

import jakarta.validation.constraints.Min;

public class RemoveOrderRequest {
    @Min(value = 1, message = "The order ID value can't be less than 1!")
    private int idOrder;

    public RemoveOrderRequest() {
    }

    public RemoveOrderRequest(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public String toString() {
        return "RemoveOrderRequest{" +
                "idOrder=" + idOrder +
                '}';
    }
}
