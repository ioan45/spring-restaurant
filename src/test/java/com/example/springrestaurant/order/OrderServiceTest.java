package com.example.springrestaurant.order;

import com.example.springrestaurant.client.Client;
import com.example.springrestaurant.client.ClientService;
import com.example.springrestaurant.menu_item.MenuItem;
import com.example.springrestaurant.menu_item.MenuItemRepository;
import com.example.springrestaurant.order.dto.MenuItemEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderedMenuItemRepository orderedMenuItemRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Test
    public void addOrderTest() {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        OrderEntity testOrder = new OrderEntity(1, new Timestamp(1), testClient);
        MenuItemEntry entry1 = new MenuItemEntry("TestItem", 2);
        List<MenuItemEntry> entries = List.of(entry1);
        when(menuItemRepository.existsByName(entry1.getItemName())).thenReturn(true);
        when(clientService.getClientById(testClient.getIdClient())).thenReturn(testClient);
        when(orderRepository.save(testOrder)).thenReturn(testOrder);
        when(menuItemRepository.findByName(entry1.getItemName())).thenReturn(Optional.of(new MenuItem()));

        orderService.addOrder(testOrder, testClient.getIdClient(), entries);

        verify(orderRepository, times(1)).save(testOrder);
        verify(orderedMenuItemRepository, times(1)).save(any());
    }

    @Test
    public void removeOrderTest() {
        OrderEntity testOrder = new OrderEntity(1, new Timestamp(1), null);
        when(orderRepository.findById(testOrder.getOrderId())).thenReturn(Optional.of(testOrder));

        orderService.removeOrder(testOrder.getOrderId());

        verify(orderRepository, times(1)).delete(testOrder);
        verify(orderedMenuItemRepository, times(1)).deleteAllByOrder(testOrder);
    }

    @Test
    public void getOrdersByClientTest() {
        Client testClient = new Client(1, "FirstName", "LastName", "email@gmail.com", "0712345678");
        OrderEntity testOrder = new OrderEntity(1, new Timestamp(1), testClient);
        List<OrderEntity> orders = List.of(testOrder);
        when(clientService.getClientById(testClient.getIdClient())).thenReturn(testClient);
        when(orderRepository.findAllByClient(testClient)).thenReturn(orders);

        List<OrderEntity> result = orderService.getOrdersByClient(testClient.getIdClient());

        assertEquals(orders, result);
    }
}
