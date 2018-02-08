package com.dub.customers.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dub.customers.exceptions.CustomerNotFoundException;
import com.dub.customers.exceptions.DuplicateCustomerException;
import com.dub.customers.model.Customer;
import com.dub.customers.model.CustomerList;
import com.dub.customers.services.CustomerService;

@RequestMapping(value = "/api")
@RestController
public class CustomerController {
	 
	@Value("${resourceUriHost}")
	private String resourceUriHost;
	
	@Value("${resourceUriPort}")
	private String resourceUriPort;
	
	@Value("${resourceUriPath}")
	private String resourceUriPath;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customerList", 
						method = RequestMethod.GET)
	public CustomerList customerList() {

		CustomerList customerList = customerService.getCustomerList();
			
		return customerList;
	}

	@RequestMapping(value = "/createCustomer", 
			method = RequestMethod.POST)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

		HttpHeaders headers = new HttpHeaders();
		
		try {
		
			Customer newCustomer = customerService.createCustomer(customer);
			
			UriComponents ucb = UriComponentsBuilder.newInstance()
			            .scheme("http")
			            .host(resourceUriHost)
			            .port(resourceUriPort)
			            .path(resourceUriPath + "/{id}")
			            .buildAndExpand(newCustomer.getId());
			            
			URI uri = ucb.toUri();
				
			headers.setLocation(uri);
		
			return new ResponseEntity<>(newCustomer, headers, HttpStatus.OK);
			
		} catch (DuplicateCustomerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
	}


	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
		
		try {
			Customer customer = customerService.getCustomerById(id);
		
			return new ResponseEntity<>(customer, HttpStatus.OK);
		
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(
										@PathVariable("id") long id, 
										@RequestBody Customer customer) {
				
		try {
			// check that customer exists
			customerService.getCustomerById(id);
			customerService.updateCustomer(customer);
			return new ResponseEntity<>(customer, HttpStatus.OK);	
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
				
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
		
		try {
			// check that customer exists
			customerService.getCustomerById(id);
			customerService.deleteCustomer(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}	
	}
}
