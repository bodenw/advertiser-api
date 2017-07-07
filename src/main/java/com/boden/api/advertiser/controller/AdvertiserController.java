package com.boden.api.advertiser.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boden.api.advertiser.model.Advertiser;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {

	private final Logger logger = LoggerFactory.getLogger(AdvertiserController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<Advertiser> getAdvertisers() {
		Collection<Advertiser> advertisers = new ArrayList<Advertiser>();
		return advertisers;
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleExceptions(Throwable t) {
		logger.error("Unhandled exception in REST api", t);;
	}
	
	
	private Advertiser mockAdvertiser() {
		Advertiser mock = new Advertiser();
		mock.setId("12345678-1234-1234-1234-123456789ABC");
		mock.setName("Name");
		mock.setContactName("contact name");
		mock.setCreditLimit(100);
		return mock;
	}
}
