package com.store.retailstore.model;

public interface Products {

    String getProductName();
    ProductType getProductType();
    double getProductUnitPrice();
    double getTotalPrice(int quantity);
}
