package com.boden.api.advertiser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boden.api.advertiser.config.DataConfig;
import com.boden.api.advertiser.model.Advertiser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AdvertiserApplication.class, DataConfig.class, TestConfig.class})
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AdvertiserApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void restEndpointForGetAllAdvertisers_shouldReturn200n() {
		String url = "/api/advertisers";
		ResponseEntity<Advertiser[]> response = restTemplate.getForEntity(url, Advertiser[].class);
		
		assertEquals("response code should be 200", HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void restEndpointForGetById_shouldReturn200_whenGoodIdIsGiven() {
		String url = "/api/advertisers/12341234-1234-1234-1234-123412341234";
		ResponseEntity<Advertiser> response = restTemplate.getForEntity(url, Advertiser.class);
		
		assertEquals("response code should be 200", HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void restEndpointForGetById_shouldReturn404_whenBadIdIsGiven() {
		String url = "/api/advertisers/99999999-9999-9999-9999-123456789ABC";
		ResponseEntity<Advertiser> response = restTemplate.getForEntity(url, Advertiser.class);
		
		assertEquals("response code should be 404", HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void restEndpointForGetById_shouldReturn400_whenBadDataIsPosted() {
		String url = "/api/advertisers";
		
		Advertiser advertiser = new Advertiser();
		advertiser.setName("testing a really long name that is longer that fifty characters");
		advertiser.setContactName("testing");
		advertiser.setCreditLimit(100);
		
		ResponseEntity<Advertiser> response = restTemplate.postForEntity(url, advertiser, Advertiser.class);
		
		assertEquals("response code should be 400", HttpStatus.BAD_REQUEST, response.getStatusCode());
		
	}
}
