package com.example.springrestaurant.order;

import com.example.springrestaurant.order.dto.AddOrderRequest;
import com.example.springrestaurant.order.dto.MenuItemEntry;
import com.example.springrestaurant.order.dto.RemoveOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private OrderService orderService;

    @Test
    public void addOrderTest() throws Exception {
        MenuItemEntry entry = new MenuItemEntry("name", 2);
        List<MenuItemEntry> entries = List.of(entry);
        AddOrderRequest req = new AddOrderRequest(1, new Timestamp(1), entries);

        mockMvc.perform(post("/order/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Order added successfully!"));

        verify(orderService, times(1)).addOrder(any(OrderEntity.class), anyInt(), anyList());
    }

    @Test
    public void removeOrderTest() throws Exception {
        RemoveOrderRequest req = new RemoveOrderRequest(1);

        mockMvc.perform(post("/order/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Order removed successfully!"));

        verify(orderService, times(1)).removeOrder(anyInt());
    }

    @Test
    public void getOrdersByClientTest() throws Exception {
        OrderEntity order = new OrderEntity(1, new Timestamp(1), null);
        List<OrderEntity> orders = List.of(order);
        when(orderService.getOrdersByClient(1)).thenReturn(orders);

        mockMvc.perform(get("/order/get/by/client")
                        .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(orders)));

        verify(orderService, times(1)).getOrdersByClient(anyInt());
    }
}
