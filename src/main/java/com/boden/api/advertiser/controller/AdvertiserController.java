package com.boden.api.advertiser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boden.api.advertiser.exception.NotFoundException;
import com.boden.api.advertiser.model.Advertiser;
import com.boden.api.advertiser.service.AdvertiserService;

@RestController
@RequestMapping("/api/advertisers")
public class AdvertiserController {

	private final Logger logger = LoggerFactory.getLogger(AdvertiserController.class);
	
	@Autowired
	private AdvertiserService advertiserService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Advertiser> getAllAdvertisers() {
		return advertiserService.retrieveAllAdvertisers();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Advertiser getAdvertiserById(@PathVariable String id) throws NotFoundException {
		Advertiser advertiser = advertiserService.retrieveAdvertiserById(id);
		if (advertiser == null) {
			throw new NotFoundException("advertiser with id " + id + " was not found");
		}
		return advertiser;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Advertiser createAdvertiser(@RequestBody Advertiser advertiser) {
		Advertiser result = advertiserService.createAdvertiser(advertiser);
		return result;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Advertiser updateAdvertiser(@PathVariable String id, @RequestBody Advertiser advertiser) throws NotFoundException {
		if (advertiserService.retrieveAdvertiserById(id) == null) {
			throw new NotFoundException("advertiser with id " + id + " was not found");
		}
		advertiser.setId(id);
		advertiser = advertiserService.updateAdvertiser(advertiser);
		return advertiser;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAdvertiser(@PathVariable String id) throws NotFoundException {
		if (advertiserService.retrieveAdvertiserById(id) == null) {
			throw new NotFoundException("advertiser with id " + id + " was not found");
		}
		advertiserService.deleteAdvertiser(id);
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNotFoundExceptions(NotFoundException e) {
		logger.warn(e.getMessage());
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleExceptions(Throwable t) {
		logger.error("Unhandled exception in REST api", t);
	}
	
}
