package com.example.springrestaurant.order;

import com.example.springrestaurant.client.Client;
import com.example.springrestaurant.client.ClientService;
import com.example.springrestaurant.menu_item.MenuItemRepository;
import com.example.springrestaurant.order.dto.MenuItemEntry;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderedMenuItemRepository orderedMenuItemRepository;
    private final ClientService clientService;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderRepository orderRepository, OrderedMenuItemRepository orderedMenuItemRepository, ClientService clientService, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderedMenuItemRepository = orderedMenuItemRepository;
        this.clientService = clientService;
        this.menuItemRepository = menuItemRepository;
    }

    public void addOrder(OrderEntity order, int idClient, List<MenuItemEntry> orderedItems) {
        for (MenuItemEntry entry : orderedItems)
            if (!menuItemRepository.existsByName(entry.getItemName()))
                throw new RuntimeException("The menu item '" + entry.getItemName() + "' doesn't exist!");

        Client client = clientService.getClientById(idClient);
        order.setClient(client);
        OrderEntity addedOrder = orderRepository.save(order);
        if (addedOrder.getOrderId() == 0)
            throw new RuntimeException("Error on adding the order!");

        for (MenuItemEntry entry : orderedItems) {
            OrderedMenuItem orderedItem = new OrderedMenuItem();
            orderedItem.setAmount(entry.getAmount());
            orderedItem.setOrder(addedOrder);
            orderedItem.setMenuItem(menuItemRepository.findByName(entry.getItemName()).get());
            orderedMenuItemRepository.save(orderedItem);
        }
    }

    @Transactional
    public void removeOrder(int idOrder) {
        OrderEntity order = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("There is no order with the specified ID!"));
        orderedMenuItemRepository.deleteAllByOrder(order);
        orderRepository.delete(order);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getOrdersByClient(int idClient) {
        Client client = clientService.getClientById(idClient);
        return orderRepository.findAllByClient(client);
    }

    public List<MenuItemEntry> getOrderedItemsByOrderId(int idOrder) {
        OrderEntity order = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("There is no order with the specified ID!"));
        List<OrderedMenuItem> orderedItems = orderedMenuItemRepository.findAllByOrder(order);

        List<MenuItemEntry> result = new ArrayList<>();
        for (OrderedMenuItem item : orderedItems)
            result.add(new MenuItemEntry(item.getMenuItem().getName(), item.getAmount()));
        return result;
    }
}
