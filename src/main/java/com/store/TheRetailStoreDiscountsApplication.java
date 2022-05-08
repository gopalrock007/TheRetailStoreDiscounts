package com.store;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.store.retailstore.model.Discounts;
import com.store.retailstore.model.Product;
import com.store.retailstore.model.ProductType;
import com.store.retailstore.model.Products;
import com.store.retailstore.model.StoreDiscounts;
import com.store.retailstore.model.User;
import com.store.retailstore.model.UserType;
import com.store.retailstore.service.Bill;
import com.store.retailstore.service.Cart;
import com.store.retailstore.service.Receipt;
import com.store.retailstore.service.ProcessingCart;

@SpringBootApplication
@ComponentScan(basePackages = {"com.store.retailstore"})
public class TheRetailStoreDiscountsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TheRetailStoreDiscountsApplication.class, args);
	
		User user = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		Products grocery = new Product("Rise", 200, ProductType.GROCERY);
		Products cloth = new Product("Shirt", 800, ProductType.CLOTHING);

		Discounts discounts = new StoreDiscounts();
		Cart cart = new ProcessingCart(discounts, user);
		cart.addProducts(grocery, 2);
		cart.addProducts(cloth, 1);

		Bill bill = new Receipt();
		bill.printBill(cart);
	}
}
