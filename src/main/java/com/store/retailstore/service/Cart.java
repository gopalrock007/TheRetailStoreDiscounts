package com.store.retailstore.service;

import com.store.retailstore.model.Products;
import com.store.retailstore.model.User;

public interface Cart {
    void addProducts(Products products, int quantity);
    int checkUserTypeAndGetDiscounts(User user);
    double totalAmount();
}
