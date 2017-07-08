package com.boden.api.advertiser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.boden.api.advertiser.model.Advertiser;

public interface AdvertiserMapper {
	@Results({
        @Result(property = "id", column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "contactName", column = "contactName"),
        @Result(property = "creditLimit", column = "creditLimit")
      })
	@Insert("INSERT INTO advertiser(id,name,contactName,creditLimit) values(#{id},#{name},#{contactName},#{creditLimit})")
	void createAdvertiser(Advertiser advertiser);
	
	@Select("SELECT id, name, contactName, creditLimit from advertiser WHERE id=#{id}")
	Advertiser retrieveAdvertiserById(String id);
	
	@Update("UPDATE advertiser SET name=#{name}, contactName=#{contactName}, creditLimit=#{creditLimit} WHERE id=#{id}")
	void updateAdvertiser(Advertiser advertiser);
	
	@Delete("DELETE FROM advertiser WHERE id=#{id}")
	void deleteAdvertiser(String id);
	
	@Select("SELECT id, name, contactName, creditLimit from advertiser")
	List<Advertiser> retrieveAllAdvertisers();
	
}
