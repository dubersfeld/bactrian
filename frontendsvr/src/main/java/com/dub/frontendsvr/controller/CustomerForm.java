package com.dub.frontendsvr.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerForm {

	@NotNull(message = "{validate.firstName.required}")
	@Size(min = 1, message = "{validate.firstName.required}")
	String firstName;
	
	@NotNull(message = "{validate.lastName.required}")
	@Size(min = 1, message = "{validate.lastName.required}")
	String lastName;
	
	@NotNull(message = "{validate.city.required}")
	@Size(min = 1, message = "{validate.city.required}")
	String city;
	

	
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

	
	
}