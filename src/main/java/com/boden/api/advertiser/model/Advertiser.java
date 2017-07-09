package com.boden.api.advertiser.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Advertiser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@NotNull(message="name is required")
	@Length(min=1, max=50, message="name must be between 1 and 50 characters in length")
	private String name;
	
	@NotNull(message="contactName is required")
	@Length(min=1, max=100, message="contactName must be between 1 and 100 characters in length")
	private String contactName;
	
	@NotNull(message="creditLimit is required")
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
