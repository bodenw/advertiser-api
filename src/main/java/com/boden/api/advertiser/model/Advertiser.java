package com.boden.api.advertiser.model;

import java.io.Serializable;

public class Advertiser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String contactName;
	private Integer creditLimit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Integer getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Integer creditLimit) {
		this.creditLimit = creditLimit;
	}
}
