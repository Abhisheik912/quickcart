package com.quickcart.controller;

import com.quickcart.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<Product> getProducts() {

        return List.of(
                new Product(1L, "Wireless Headphones", 2499.00),
                new Product(2L, "Mechanical Keyboard", 3499.00),
                new Product(3L, "Gaming Mouse", 1499.00));
    }
}