package com.boden.api.advertiser.service;

import com.boden.api.advertiser.model.Advertiser;

public interface AdvertiserService {
	public Advertiser createAdvertiser(Advertiser advertiser);
	public Advertiser retrieveAdvertiserById(String advertiserId);
	public Advertiser updateAdvertiser(Advertiser advertiser);
	public void deleteAdvertiser(String advertiserId);
	
}
