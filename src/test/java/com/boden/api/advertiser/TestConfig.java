package com.boden.api.advertiser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.boden.api.advertiser.controller.AdvertiserController;
import com.boden.api.advertiser.service.AdvertiserService;
import com.boden.api.advertiser.service.AdvertiserServiceImpl;

@Configuration
public class TestConfig {
	@Bean
	public AdvertiserController advertiserController() {
		return new AdvertiserController();
	}
	
	@Bean 
	public AdvertiserService advertiserService() {
		return new AdvertiserServiceImpl();
	}
}
