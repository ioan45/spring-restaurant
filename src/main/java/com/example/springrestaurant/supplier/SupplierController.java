package com.example.springrestaurant.supplier;

import com.example.springrestaurant.order.OrderEntity;
import com.example.springrestaurant.supplier.dto.AddSupplierRequest;
import com.example.springrestaurant.supplier.dto.SupplierDtoMapper;
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

@Tag(name = "Supplier", description = "APIs to manage the suppliers of menu item ingredients.")
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Operation(
            summary = "Add a supplier for menu item ingredients.",
            description = "Add an Supplier object in the database. The response is a simple message signaling success or failure.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @PostMapping("/add")
    public ResponseEntity<String> addSupplier(
            @RequestBody @Valid AddSupplierRequest req
    ) {
        Supplier newSupplier = SupplierDtoMapper.addRequest(req);
        supplierService.addSupplier(newSupplier);
        return ResponseEntity.ok().body("Ingredients supplier added successfully!");
    }

    @Operation(
            summary = "Get all the recorded suppliers.",
            description = "Get a list of Supplier objects with all the ingredients suppliers in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Supplier.class)), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain") }) })
    @GetMapping("/get/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers());
    }
}
