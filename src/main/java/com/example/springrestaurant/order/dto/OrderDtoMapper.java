package com.example.springrestaurant.order.dto;

import com.example.springrestaurant.order.OrderEntity;

public class OrderDtoMapper {
    public static OrderEntity addRequest(AddOrderRequest req) {
        return new OrderEntity(
                0,
                req.getDate(),
                null
        );
    }
}
