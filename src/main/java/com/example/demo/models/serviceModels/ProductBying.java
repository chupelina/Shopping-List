package com.example.demo.models.serviceModels;

import java.math.BigDecimal;

public class ProductBying {
    private Long id;
    private String name;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public ProductBying setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductBying setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductBying setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
