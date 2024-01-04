package com.example.springrestaurant.order;

import com.example.springrestaurant.menu_item.MenuItem;
import com.example.springrestaurant.order.dto.AddOrderRequest;
import com.example.springrestaurant.order.dto.MenuItemEntry;
import com.example.springrestaurant.order.dto.OrderDtoMapper;
import com.example.springrestaurant.order.dto.RemoveOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order", description = "APIs to manage orders made by clients for menu items.")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Add a client order for menu items.",
            description = "Add an OrderEntity object in the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/order/add")
    public ResponseEntity<String> addOrder(
            @RequestBody @Valid AddOrderRequest req
    ) {
        OrderEntity order = OrderDtoMapper.addRequest(req);
        orderService.addOrder(order, req.getIdClient(), req.getMenuItems());
        return ResponseEntity.ok().body("Order added successfully!");
    }

    @Operation(
            summary = "Remove a client order for menu items.",
            description = "Remove an OrderEntity object from the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/order/remove")
    public ResponseEntity<String> removeOrder(
            @RequestBody @Valid RemoveOrderRequest req
    ) {
        orderService.removeOrder(req.getIdOrder());
        return ResponseEntity.ok().body("Order removed successfully!");
    }

    @Operation(
            summary = "Get all the orders made by clients.",
            description = "Get a list of OrderEntity objects with all the client orders in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = OrderEntity.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/order/get/all")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @Operation(
            summary = "Get all the orders made by a specified client.",
            description = "Get a list of OrderEntity objects with all the orders made by a client specified through its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = OrderEntity.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/order/get/by/client")
    public ResponseEntity<List<OrderEntity>> getOrdersByClient(
            @RequestParam int id
    ) {
        return ResponseEntity.ok().body(orderService.getOrdersByClient(id));
    }

    @Operation(
            summary = "Get the ordered menu items for a specified order.",
            description = "Get a list of MenuItemEntry objects with all the menu items corresponding to an order specified through its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = MenuItemEntry.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/ordered-items/get/by/order-id")
    public ResponseEntity<List<MenuItemEntry>> getOrderedItemsByOrderId(
            @RequestParam int id
    ) {
        return ResponseEntity.ok().body(orderService.getOrderedItemsByOrderId(id));
    }
}
