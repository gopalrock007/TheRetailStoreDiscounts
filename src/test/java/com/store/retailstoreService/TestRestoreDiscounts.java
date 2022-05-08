package com.store.retailstoreService;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.store.retailstore.controller.RestoreControllerTest;
import com.store.retailstore.model.Discounts;
import com.store.retailstore.model.Product;
import com.store.retailstore.model.ProductType;
import com.store.retailstore.model.Products;
import com.store.retailstore.model.StoreDiscounts;
import com.store.retailstore.model.User;
import com.store.retailstore.model.UserType;
import com.store.retailstore.service.Cart;
import com.store.retailstore.service.ProcessingCart;

public class TestRestoreDiscounts {

	private static final Logger logger = LoggerFactory.getLogger(RestoreControllerTest.class);

	private Products groceryItem;
	private Products otherItem;
	private User employee;
	private User affiliate;
	private User simpleUser;
	private User simpleUserWith2Years;
	private Discounts discounts;

	@Before
	public void setUp() {
		employee = new User("Rohit", "9876543210", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		affiliate = new User("Virat", "9988776655", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		simpleUser = new User("Rishabh", "9876512345", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		simpleUserWith2Years = new User("Suresh", "9898989898", LocalDateTime.of(2018, Month.MARCH, 22, 18, 15),
				LocalDateTime.now(), UserType.GENERAL);

		groceryItem = new Product("Soap", 40, ProductType.GROCERY);
		otherItem = new Product("Mobile", 5000, ProductType.ELECTRONICS);
		discounts = new StoreDiscounts();
	}

	@Test
	public void testEmployeeWithGrocery() {
		logger.debug("TestDiscounts inside test_employeeWithGrocery() method...");
		Cart cart = new ProcessingCart(discounts, employee);
		cart.addProducts(groceryItem, 4);
		 //The percentage based discounts do not apply on groceries. 4*40=160.
		//every	$100	on	the	bill,	there	would	be	a	$	5	discount
		assertEquals(155, cart.totalAmount(), 0.01);

	}

	@Test
	public void testEmployeeWithOtherItem() {
		Cart cart = new ProcessingCart(discounts, employee);
		cart.addProducts(otherItem, 4);
		cart.addProducts(groceryItem, 1);
		
		 /*
         *  30% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 5000 * 4 = 20000
         *  After 30% discount = 14000.0
         *  Added grocery = 14040
         *  every	$100	on	the	bill,	there	would	be	a	$	5	discount
         *  After 140*5 dollars off due to price over $700.0 = 13300.0
         */
	
		assertEquals(13340.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void testAffiliateWithGrocery() {
		Cart cart = new ProcessingCart(discounts, affiliate);
		cart.addProducts(groceryItem, 5);
		//The percentage based discounts do not apply on groceries. 5*40=200
		//every	$100	on	the	bill,	there	would	be	a	$	5	discount
		assertEquals(190, cart.totalAmount(), 0.01);

	}

	@Test
	public void testAffiliateWithOtherItem() {
		Cart cart = new ProcessingCart(discounts, affiliate);
		cart.addProducts(otherItem, 4);
		 /*
         *  30% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 5000 * 4 = 20000
         *  After 10% discount = 18000.0
         *  every	$100	on	the	bill,	there	would	be	a	$	5	discount
         *  After 180*5 dollars off due to price over $900.0 = 17100.0
         */
		assertEquals(17100.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void testSimpleUserWithGrocery() {
		Cart cart = new ProcessingCart(discounts, simpleUser);
		cart.addProducts(groceryItem, 2);
		// The percentage based discounts do not apply on groceries.2*40=80
		assertEquals(80, cart.totalAmount(), 0.01);

	}

	@Test
	public void testSimpleUserWithOtherItem() {
		Cart cart = new ProcessingCart(discounts, simpleUser);
		cart.addProducts(otherItem, 4);
		/*
         *  Total 5000 * 4 = 20000
         *  No percentage discount because user is a customer for less than 2 years
         *  A	user can	get	only	one	of	the	percentage	based	discounts	on	a	bill.
         *  After 950 dollars off due to price = 19050.0 
         */
		assertEquals(19000.0, cart.totalAmount(), 0.01);
	}

	@Test
	public void testSimpleUserWith2YearsWithGrocery() {
		Cart cart = new ProcessingCart(discounts, simpleUserWith2Years);
		cart.addProducts(groceryItem, 6);
		// The percentage based discounts do not apply on groceries.6*40=240
		//every	$100	on	the	bill,	there	would	be	a	$	5	discount
		//240 -10 =230
		assertEquals(230, cart.totalAmount(), 0.01);

	}

	@Test
	public void testSimpleUserWith2YearsWithOtherItem() {
		Cart cart = new ProcessingCart(discounts, simpleUserWith2Years);
		cart.addProducts(otherItem, 4);
		/*
		 * 5% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 5000 * 4 = 20000 After 5% discount = 19000 After 950 dollars off
		 * due to price over $19000 =18050.0
		 */
		assertEquals(18050.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void testEmployeeWithGroceryAndOtherItem() {
		Cart cart = new ProcessingCart(discounts, employee);
		cart.addProducts(groceryItem, 2);
		cart.addProducts(otherItem, 2);
		/*
		 * Total (40 * 2) + (5000 * 2) = 80 + 10000=10080
		 * After 30% discount on 2 other items total 3000 discount(10000-3000)  = 7000.0
		 * total spend after % discount = 7080
		 * 350 dollars(5%discount) for 7080
		 * off due to price over $7080-350 =6730.0
		 */
		assertEquals(6730.0, cart.totalAmount(), 0.01);

	}
}
