package com.store.retailstoreService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.TheRetailStoreDiscountsApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TheRetailStoreDiscountsApplication.class)
@AutoConfigureMockMvc
public abstract class RetailStoreTest {

	protected static MediaType contentType = MediaType.APPLICATION_JSON;

	protected ObjectMapper objectMpapper = new ObjectMapper();

	@Autowired
	protected MockMvc mockMvc;

	protected String getValueAsJson(Object value) {

		try {
			return objectMpapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			return null;
		}

	}

	@Test
	void contextLoads() {
	}

}
