package com.quickcart.model;

public class Order {

    private Long orderId;
    private Long productId;
    private int quantity;
    private String status;

    public Order(Long orderId, Long productId, int quantity, String status) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}