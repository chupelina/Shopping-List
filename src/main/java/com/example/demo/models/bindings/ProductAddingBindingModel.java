package com.example.demo.models.bindings;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class ProductAddingBindingModel {
    @Length(min=3, max=20, message = "Name length must be between 3 and 20 characters!")
    @NotEmpty
    private String name;
    @Positive(message = "Price must be positive number!")
    @NotNull
    private BigDecimal price;
    @Length(min=5, message = "Description must be more than 5 characters!")
    @NotEmpty
    private String description;
    @FutureOrPresent(message = "The date cannpt be in the past!")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;
    @NotEmpty(message = "Category is required!")
    private String category;

    public String getName() {
        return name;
    }

    public ProductAddingBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddingBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddingBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductAddingBindingModel setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductAddingBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }
}
