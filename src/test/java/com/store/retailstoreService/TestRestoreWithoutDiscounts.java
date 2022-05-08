package com.store.retailstoreService;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.store.retailstore.model.Discounts;
import com.store.retailstore.model.Product;
import com.store.retailstore.model.ProductType;
import com.store.retailstore.model.Products;
import com.store.retailstore.model.StoreDiscounts;
import com.store.retailstore.model.User;
import com.store.retailstore.model.UserType;
import com.store.retailstore.service.Cart;
import com.store.retailstore.service.ProcessingCart;

public class TestRestoreWithoutDiscounts {

	private Cart cart;
	private Products products;
	private Discounts discounts;

	@Before
	public void setUp() {
		User user = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		discounts = new StoreDiscounts();
		cart = new ProcessingCart(discounts, user);
		products = new Product("something", 9, ProductType.SPORTS);
	}

	@Test
	public void testEmptyCartCostsZero() {
		assertEquals(0, cart.totalAmount(), 0.01);
	}

	@Test
	public void testSingleItemCostsUnitPrice() {
		cart.addProducts(products, 1);
		assertEquals(products.getProductUnitPrice(), cart.totalAmount(), 0.01);
	}

	@Test
	public void testMultipleBasicItemsCost() {
		int quantity = 3;
		cart.addProducts(products, quantity);
		assertEquals(quantity * products.getProductUnitPrice(), cart.totalAmount(), 0.01);
	}

	@Test
	public void testSeparatelyAdd() {
		int quantity = 3;
		for (int i = quantity; i > 0; i--) {
			cart.addProducts(products, 1);
		}
		assertEquals(quantity * products.getProductUnitPrice(), cart.totalAmount(), 0.01);
	}
}
