package com.quickcart.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private final ProductController controller = new ProductController();

    @Test
    void getProductsShouldReturnThreeProducts() {

        var products = controller.getProducts();

        assertEquals(3, products.size());
        assertEquals("Wireless Headphones", products.get(0).getName());
        assertEquals(2499.00, products.get(0).getPrice());
    }
}