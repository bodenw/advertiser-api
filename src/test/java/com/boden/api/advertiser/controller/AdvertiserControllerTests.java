package com.boden.api.advertiser.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
	private static final Integer MOCKED_CREDIT_LIMIT = new Integer(100);
	
	@Autowired
	AdvertiserController advertiserController;
	
	@Autowired
	AdvertiserService advertiserService;
	
	// getAllAdvertisers tests
	@Test
	public void getAllAdvertisers_shouldReturnRecordsFromDB() {
		List<Advertiser> advertisers = advertiserController.getAllAdvertisers();
		assertNotNull("advertisers should not be null", advertisers);
		assertFalse("advertisers should not be empty", advertisers.isEmpty());
	}
	
	// getAdvertiser tests
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
	
	// hasEnoughCredit tests
	@Test
	public void hasEnoughCredit_shouldReturnTrue_whenInputIsLessThanCreditLimit() throws NotFoundException {
		assertTrue("should be true when creditLimit is greater than amount", 
				advertiserController.hasEnoughCredit("12341234-1234-1234-1234-123412341234", 999));
	}
	
	@Test
	public void hasEnoughCredit_shouldReturnTrue_whenInputIsEqualToCreditLimit() throws NotFoundException {
		assertTrue("should be true when creditLimit is equal than amount", 
				advertiserController.hasEnoughCredit("12341234-1234-1234-1234-123412341234", 1000));
	}
	
	@Test
	public void hasEnoughCredit_shouldReturnFalse_whenInputIsLessThanCreditLimit() throws NotFoundException {
		assertFalse("should be false when creditLimit is less than amount",
				advertiserController.hasEnoughCredit("12345678-1234-1234-1234-123456789ABC", 1001));
	}
	
	@Test(expected=NotFoundException.class)
	public void hasEnoughCredit_shouldThrowNotFoundException_whenTryingToAccessRecordThatDoesNotExist() throws NotFoundException {
		assertFalse(advertiserController.hasEnoughCredit("fake_id", 1));
	}
	
	// createAdvertiser tests
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
	
	// updateAdvertiser tests
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
	
	// deleteAdvertiser tests
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
	
	// helper methods
	private Advertiser mockAdvertiser() {
		Advertiser mock = new Advertiser();
		mock.setName(MOCKED_NAME);
		mock.setContactName(MOCKED_CONTACT_NAME);
		mock.setCreditLimit(MOCKED_CREDIT_LIMIT);
		
		return mock;
	}
	
}
