package com.store.retailstore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import com.store.retailstore.controller.RetailStoreController;
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

@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackages = { "com.store.retailstore.controller", "com.store.retailstore.service",
		"com.store.retailstore.model" })
public class RestoreControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(RestoreControllerTest.class);

	@InjectMocks
	RetailStoreController retailStoreController;

	@Mock
	StoreService storeService;
	@Mock
	StoreDiscounts storeDiscounts;

	@Mock
	Discounts discounts;

	@Mock
	ProcessingCart processingCart;

	@Mock
	Cart cart;
	@Mock
	Products groceryItem;
	@Mock
	Products otherItem;
	@Mock
	User employee;
	@Mock
	User affiliate;
	@Mock
	User simpleUser;
	@Mock
	User simpleUserWith2Years;
	@Mock
	Discounts discount;

	@Mock
	Products jersey;

	@Mock
	Products tv;

	@Before
	public void setUp() {
		employee = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		affiliate = new User("Virat", "9988776655", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		simpleUser = new User("Rishabh", "7766554433", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		simpleUserWith2Years = new User("Sachin", "9922334455", LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30),
				LocalDateTime.now(), UserType.GENERAL);

		groceryItem = new Product("Soap", 20, ProductType.GROCERY);
		otherItem = new Product("TV", 222, ProductType.ELECTRONICS);
		discounts = new StoreDiscounts();

	}

	@Test
	public void testGetAmount() {
		logger.debug("RestoreControllerTest inside testGetAmount() method...");
		employee = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		discount = new StoreDiscounts();
		Cart cart = new ProcessingCart(discount, employee);
		cart.addProducts(groceryItem, 1);
		cart.addProducts(jersey, 3);
		cart.addProducts(tv, 1);
		when(storeService.totalAmount(any())).thenReturn("Total Amount To Be Paid: " + 4620.0);
		String actualValue = retailStoreController.getAmount();
		Assert.assertEquals("Total Amount To Be Paid: " + 4620.0, actualValue);

	}

}
