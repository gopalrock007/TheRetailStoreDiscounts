package com.store.retailstore.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import com.store.retailstore.model.Discounts;
import com.store.retailstore.model.ProductType;
import com.store.retailstore.model.Products;
import com.store.retailstore.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ComponentScan(basePackages = { "com.store.retailstore.constant" })
public class ProcessingCart implements Cart {

	private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

	@Getter
	@Setter
	private Map<Products, Integer> productQuantityMap;
	private Discounts discounts;
	private User user;

	public ProcessingCart(Discounts discounts, User user) {
		productQuantityMap = new LinkedHashMap<>();
		this.discounts = discounts;
		this.user = user;
	}

	@Override
	public void addProducts(Products products, int quantity) {
		logger.debug("ProcessingCart inside addProducts(products, quantity) method...");
		int storedQuantity = productQuantityMap.getOrDefault(products, 0);
		productQuantityMap.put(products, storedQuantity + quantity);
	}

	@Override
	public int checkUserTypeAndGetDiscounts(User user) {
		logger.debug("ProcessingCart inside checkUserTypeAndGetDiscounts(User user) method...");
		int returnValue;

		switch (user.getUserType()) {
		case EMPLOYEE:
			returnValue = 30;
			break;
		case AFFILIATE:
			returnValue = 10;
			break;
		case GENERAL:
			if (ChronoUnit.YEARS.between(user.getRegisteredDateAndTime(), LocalDateTime.now()) >= 2) {
				returnValue = 5;
				break;
			}
		default:
			returnValue = 0;
			break;
		}

		return returnValue;
	}

	@Override
	public double totalAmount() {
		logger.debug("ProcessingCart inside totalAmount() method...");
		double total = 0;
		for (Products product : productQuantityMap.keySet()) {
			double productPrice = product.getTotalPrice(productQuantityMap.get(product));
			if (product.getProductType() != ProductType.GROCERY) {
				total += (productPrice - (productPrice * checkUserTypeAndGetDiscounts(user) / 100));
			} else {
				total += productPrice;
			}

		}

		if (discounts != null)
			total = discounts.applyDiscount(total);

		return total;
	}
}
