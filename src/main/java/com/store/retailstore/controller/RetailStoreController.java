package com.store.retailstore.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.retailstore.model.Discounts;
import com.store.retailstore.model.Product;
import com.store.retailstore.model.ProductType;
import com.store.retailstore.model.Products;
import com.store.retailstore.model.StoreDiscounts;
import com.store.retailstore.model.User;
import com.store.retailstore.model.UserType;
import com.store.retailstore.service.Cart;
import com.store.retailstore.service.ProcessingCart;
import com.store.retailstore.service.StoreService;

@Component
@RestController
@ComponentScan(basePackages = { "com.store.retailstore.service" })
public class RetailStoreController {

	private static final Logger logger = LoggerFactory.getLogger(RetailStoreController.class);

	@Autowired
	StoreService storeService;

	@GetMapping("/restore/amount")
	public String getAmount() {
		logger.debug("RetailStoreController inside getAmount() method...");
		User employee = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		Products grocery = new Product("Sugar", 100, ProductType.GROCERY);
		Products bat = new Product("Bat", 1600, ProductType.SPORTS);
		Products tv = new Product("TV", 25000, ProductType.ELECTRONICS);

		Discounts discount = new StoreDiscounts();
		Cart cart = new ProcessingCart(discount, employee);
		cart.addProducts(grocery, 1);
		cart.addProducts(bat, 3);
		cart.addProducts(tv, 1);
		return storeService.totalAmount(cart);

	}


}
