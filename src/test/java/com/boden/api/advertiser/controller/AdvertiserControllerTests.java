package com.boden.api.advertiser.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boden.api.advertiser.TestConfig;
import com.boden.api.advertiser.config.DataConfig;
import com.boden.api.advertiser.exception.NotFoundException;
import com.boden.api.advertiser.model.Advertiser;
import com.boden.api.advertiser.service.AdvertiserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfig.class, TestConfig.class})
public class AdvertiserControllerTests {
	
	private static final String MOCKED_NAME = "MOCKED_NAME";
	private static final String MOCKED_CONTACT_NAME = "MOCKED_CONTACT_NAME";
	private static final Integer MOCKED_CREDIT_LIMIT = new Integer(10000);
	
	@Autowired
	AdvertiserController advertiserController;
	
	@Autowired
	AdvertiserService advertiserService;
	
	@Test
	public void getAllAdvertisers_shouldReturnRecordsFromDB() {
		List<Advertiser> advertisers = advertiserController.getAllAdvertisers();
		assertNotNull(advertisers);
		assertFalse(advertisers.isEmpty());
	}
	
	@Test
	public void getAdvertiser_shouldReturnDataFromDatabase_whenMatchingRecordExists() throws NotFoundException {
		Advertiser advertiser = advertiserController.getAdvertiserById("12345678-1234-1234-1234-123456789ABC");
		assertNotNull("advertiser should not be null", advertiser);
		assertEquals("advertiser id should match", "12345678-1234-1234-1234-123456789ABC", advertiser.getId());
		assertEquals("advertiser name should match", "Testing", advertiser.getName());
		assertEquals("advertiser contact name should match", "Test User", advertiser.getContactName());
		assertEquals("advertiser credit limit should match", new Integer(1000), advertiser.getCreditLimit());
	}
	
	@Test(expected=NotFoundException.class)
	public void getAdvertiser_shouldThrowNotFoundException_whenMatchingRecordDoesNotExist() throws NotFoundException {
		advertiserController.getAdvertiserById("11111111-1111-1111-1111-111111111111");
	}
	
	@Test
	public void createAdvertiser_shouldSuccessfullyCreateAdvertiser() throws NotFoundException {
		Advertiser advertiser = mockAdvertiser();
		advertiser = advertiserController.createAdvertiser(advertiser);
		
		assertNotNull(advertiser.getId());
		
		String advertiserId = advertiser.getId();
		
		Advertiser advertiserFromDB = advertiserController.getAdvertiserById(advertiserId);
		
		assertEquals("advertiser name should match", MOCKED_NAME, advertiserFromDB.getName());
		assertEquals("advertiser contact name should match", MOCKED_CONTACT_NAME, advertiserFromDB.getContactName());
		assertEquals("advertiser credit limit should match", MOCKED_CREDIT_LIMIT, advertiserFromDB.getCreditLimit());
	}
	
	@Test 
	public void updateAdvertiser_shouldSuccessfullyUpdateAdvertiser() throws NotFoundException {
		// Make sure data is initially different
		Advertiser advertiserFromDB = advertiserService.retrieveAdvertiserById("12345678-1234-1234-1234-123456789ABC");
		assertFalse(MOCKED_NAME.equals(advertiserFromDB.getName()));
		assertFalse(MOCKED_CONTACT_NAME.equals(advertiserFromDB.getContactName()));
		assertFalse(MOCKED_CREDIT_LIMIT.equals(advertiserFromDB.getCreditLimit()));
		
		advertiserController.updateAdvertiser("12345678-1234-1234-1234-123456789ABC", mockAdvertiser());
		
		advertiserFromDB = advertiserService.retrieveAdvertiserById("12345678-1234-1234-1234-123456789ABC");
		
		assertEquals("advertiser name should match", MOCKED_NAME, advertiserFromDB.getName());
		assertEquals("advertiser contact name should match", MOCKED_CONTACT_NAME, advertiserFromDB.getContactName());
		assertEquals("advertiser credit limit should match", MOCKED_CREDIT_LIMIT, advertiserFromDB.getCreditLimit());
	}
	
	@Test(expected=NotFoundException.class)
	public void updateAdvertiser_shouldThrowNotFoundException_whenTryingToUpdateRecordThatDoesNotExist() throws NotFoundException {
		advertiserController.updateAdvertiser("fake_id", mockAdvertiser());
	}
	
	@Test
	public void deleteAdvertiser_shouldSuccessfullyDeleteAdvertiser() throws NotFoundException {
		// create record to delete
		Advertiser advertiser = advertiserService.createAdvertiser(mockAdvertiser());
		String advertiserId = advertiser.getId();
		
		// Make sure that it is in the DB
		assertNotNull(advertiserService.retrieveAdvertiserById(advertiserId));
		
		advertiserController.deleteAdvertiser(advertiserId);
		
		// Make sure that it is not in the DB after delete
		assertNull(advertiserService.retrieveAdvertiserById(advertiserId));
	}
	
	@Test(expected=NotFoundException.class)
	public void deleteAdvertiser_shouldThrowNotFoundException_whenTryingToDeleteRecordThatDoesNotExist() throws NotFoundException {
		advertiserController.deleteAdvertiser("fake_id");
	}
	
	private Advertiser mockAdvertiser() {
		Advertiser mock = new Advertiser();
		mock.setName(MOCKED_NAME);
		mock.setContactName(MOCKED_CONTACT_NAME);
		mock.setCreditLimit(MOCKED_CREDIT_LIMIT);
		
		return mock;
	}
	
}
