package com.boden.api.advertiser.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.boden.api.advertiser.model.Advertiser;

public class AdvertiserTests {
	
	public static final String MOCK_ADVERTISER_ID = "12345678-1234-1234-1234-123456789ABC";
	public static final String MOCK_ADVERTISER_NAME = "AdvertiserName";
	public static final String MOCK_ADVERTISER_CONTACT_NAME = "John Doe";
	public static final Integer MOCK_ADVERTISER_CREDIT_LIMIT = 100;
	
	@Test
	public void gettersAndSetter_shouldWork() {
		Advertiser advertiser = new Advertiser();
		advertiser.setId(MOCK_ADVERTISER_ID);
		advertiser.setName(MOCK_ADVERTISER_NAME);
		advertiser.setContactName(MOCK_ADVERTISER_CONTACT_NAME);
		advertiser.setCreditLimit(MOCK_ADVERTISER_CREDIT_LIMIT);
	
		assertEquals("id should match", MOCK_ADVERTISER_ID, advertiser.getId());
		assertEquals("name should match", MOCK_ADVERTISER_NAME, advertiser.getName());
		assertEquals("contactName should match", MOCK_ADVERTISER_CONTACT_NAME, advertiser.getContactName());
		assertEquals("creditLimit should match", MOCK_ADVERTISER_CREDIT_LIMIT, advertiser.getCreditLimit());
		
	}
}
