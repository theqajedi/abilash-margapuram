package com.swagger.petstore.test.api.model;

import java.util.Date;

public class OrderBuilder {
    private long id;
    private long petId;
    private int quantity;
    private Date shipDate;
    private String status;
    private boolean complete;

    public OrderBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setPetId(long petId) {
        this.petId = petId;
        return this;
    }

    public OrderBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder setShipDate(Date shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public Order createOrder() {
        return new Order(id, petId, quantity, shipDate, status, complete);
    }
}