package com.boden.api.advertiser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boden.api.advertiser.mapper.AdvertiserMapper;
import com.boden.api.advertiser.model.Advertiser;

@Service("advertiserService")
public class AdvertiserServiceImpl implements AdvertiserService {

	@Autowired
	private AdvertiserMapper advertiserMapper;
	
	@Override
	public Advertiser createAdvertiser(Advertiser advertiser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertiser retrieveAdvertiserById(String advertiserId) {
		return advertiserMapper.retrieveAdvertiserById(advertiserId);
	}

	@Override
	public Advertiser updateAdvertiser(Advertiser advertiser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdvertiser(String advertiserId) {
		// TODO Auto-generated method stub
		
	}
	
}
