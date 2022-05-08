package com.store.retailstore.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

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
@ComponentScan(basePackages = { "com.store.retailstore.service", "com.store.retailstore.model" })
public class ProcessingCartTest {
	private static final Logger logger = LoggerFactory.getLogger(ProcessingCartTest.class);

	@Mock
	ProcessingCart processingCart;

	@Mock
	StoreService storeService;

	@Mock
	StoreDiscounts storeDiscounts;

	@Mock
	Discounts discounts;

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

	@Before
	public void setUp() {
		employee = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		affiliate = new User("Virat", "9988776655", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		simpleUser = new User("Rishabh", "9898989898", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		simpleUserWith2Years = new User("Raju", "9876512345", LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30),
				LocalDateTime.now(), UserType.GENERAL);

		groceryItem = new Product("Soap", 20, ProductType.GROCERY);
		otherItem = new Product("TV", 5000, ProductType.ELECTRONICS);// 222
		discounts = new StoreDiscounts();
	}

	@Test
	public void testCheckUserTypeAndGetDiscountsWithEmployee() {
		logger.debug("ProcessingCartTest inside test_employeeWithGrocery() method...");
		employee = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		discount = new StoreDiscounts();
		Cart cart = new ProcessingCart(discount, employee);
		cart.addProducts(groceryItem, 4);
		int actualValue = cart.checkUserTypeAndGetDiscounts(employee);
		Assert.assertEquals(30, actualValue);
	}

	@Test
	public void testCheckUserTypeAndGetDiscountsWithAffiliate() {
		logger.debug("ProcessingCartTest inside test_employeeWithGrocery() method...");
		affiliate = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		discount = new StoreDiscounts();
		Cart cart = new ProcessingCart(discount, affiliate);
		cart.addProducts(otherItem, 4);
		int actualValue = cart.checkUserTypeAndGetDiscounts(affiliate);
		Assert.assertEquals(10, actualValue);
	}

	@Test
	public void testCheckUserTypeAndGetDiscountsWithGeneral() {
		logger.debug("ProcessingCartTest inside test_employeeWithGrocery() method...");
		simpleUser = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		discount = new StoreDiscounts();
		Cart cart = new ProcessingCart(discount, simpleUser);
		cart.addProducts(otherItem, 4);
		int actualValue = cart.checkUserTypeAndGetDiscounts(simpleUser);
		Assert.assertEquals(0, actualValue);
	}

}
