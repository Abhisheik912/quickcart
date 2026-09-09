package com.quickcart.controller;

import com.quickcart.model.Order;
import com.quickcart.model.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest request) {

        return new Order(
                1001L,
                request.getProductId(),
                request.getQuantity(),
                "CONFIRMED");
    }
}