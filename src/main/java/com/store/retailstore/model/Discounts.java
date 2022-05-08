package com.store.retailstore.model;

public interface Discounts {
    DiscountType getDiscountType();
    double applyDiscount(double amount);
}
