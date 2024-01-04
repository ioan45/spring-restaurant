package com.example.springrestaurant.supplier;

import com.example.springrestaurant.supplier.dto.AddSupplierRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(SupplierController.class)
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private SupplierService supplierService;

    @Test
    public void addSupplierTest() throws Exception {
        AddSupplierRequest req = new AddSupplierRequest("name", "email@gmail.com", "0253.345678");

        mockMvc.perform(post("/supplier/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().string("Ingredients supplier added successfully!"));

        verify(supplierService, times(1)).addSupplier(any(Supplier.class));
    }

    @Test
    public void getAllSuppliersTest() throws Exception {
        Supplier supplier = new Supplier(1, "name", "email@gmail.com", "0254.345678");
        List<Supplier> suppliers = List.of(supplier);
        when(supplierService.getAllSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/supplier/get/all"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(suppliers)));

        verify(supplierService, times(1)).getAllSuppliers();
    }
}
