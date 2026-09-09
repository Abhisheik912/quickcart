package com.quickcart.controller;

import com.quickcart.model.Order;
import com.quickcart.model.OrderRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {

    private final OrderController controller = new OrderController();

    @Test
    void createOrderShouldReturnConfirmedOrder() {

        OrderRequest request = new OrderRequest();
        request.setProductId(1L);
        request.setQuantity(2);

        Order order = controller.createOrder(request);

        assertEquals(1001L, order.getOrderId());
        assertEquals(1L, order.getProductId());
        assertEquals(2, order.getQuantity());
        assertEquals("CONFIRMED", order.getStatus());
    }
}