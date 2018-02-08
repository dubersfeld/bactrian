package com.dub.frontendsvr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	private long id;
	private String firstName;
	private String lastName;
	private String city;
	
	public Customer() {}
	
	public Customer(long id, String firstName, String lastName, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.city = city;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return id + " " + firstName + " " + lastName + " " + city;
	}
	
	
}
