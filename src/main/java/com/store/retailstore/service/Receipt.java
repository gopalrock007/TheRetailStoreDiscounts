package com.store.retailstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.store.retailstore.model.Products;

public class Receipt implements Bill{
	private static final Logger logger = LoggerFactory.getLogger(Receipt.class);
    @Override
    public void printBill(Cart cart) {
    	logger.debug("Receipt inside printBill(cart) method...");
        for(Products products: ((ProcessingCart)cart).getProductQuantityMap().keySet()) {
            System.out.println(products.getProductName() + " :: " + products.getProductType() + " :: " + products.getProductUnitPrice() + " :: "+ ((ProcessingCart)cart).getProductQuantityMap().get(products) + " :: " + products.getTotalPrice(((ProcessingCart)cart).getProductQuantityMap().get(products)));
        }

        System.out.println("Total Amount After Discount :: " + cart.totalAmount());

    }
}
