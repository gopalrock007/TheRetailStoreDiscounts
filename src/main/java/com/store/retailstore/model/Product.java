package com.store.retailstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product implements Products {

    private final String productName ;
    private final double productUnitPrice ;
    private final ProductType productType;
    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public ProductType getProductType() {
        return productType;
    }

    @Override
    public double getProductUnitPrice() {
        return productUnitPrice;
    }

    @Override
    public double getTotalPrice(int quantity) {
        return productUnitPrice * quantity;
    }
}
