package com.store.retailstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
	private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

	public String totalAmount(Cart cart) {
		logger.debug("StoreService inside totalAmount(Cart cart) method...");
		double amountToPay = cart.totalAmount();
		return "Total Amount To Be Paid: " + amountToPay;
	}

}
