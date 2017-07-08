package com.boden.api.advertiser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boden.api.advertiser.model.Advertiser;
import com.boden.api.advertiser.service.AdvertiserService;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {

	private final Logger logger = LoggerFactory.getLogger(AdvertiserController.class);
	
	@Autowired
	private AdvertiserService advertiserService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Advertiser getAdvertiserById(@RequestParam(required = true, value="id") String advertiserId) {
		Advertiser advertiser = advertiserService.retrieveAdvertiserById(advertiserId);
		return advertiser;
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleExceptions(Throwable t) {
		logger.error("Unhandled exception in REST api", t);
	}
	
}
