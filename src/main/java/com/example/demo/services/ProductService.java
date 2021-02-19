package com.example.demo.services;

import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.serviceModels.ProductBying;
import com.example.demo.models.serviceModels.ProductServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel product);

    void buyCurrentProduct(Long id);

    BigDecimal getTotalPrice();

    List<ProductBying> findAllByCategory(String category);

    void buyAllProducts();
}
