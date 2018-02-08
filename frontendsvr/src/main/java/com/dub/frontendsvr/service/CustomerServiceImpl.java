package com.dub.frontendsvr.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.dub.frontendsvr.domain.Customer;
import com.dub.frontendsvr.domain.CustomerList;
import com.dub.frontendsvr.exceptions.CustomerAccessDeniedException;
import com.dub.frontendsvr.exceptions.CustomerNotFoundException;
import com.dub.frontendsvr.exceptions.DuplicateCustomerException;
import com.dub.frontendsvr.exceptions.UnauthorizedException;

@Service
public class CustomerServiceImpl implements CustomerService {

	String resourceUri; 
	
	@Value("${resourceUriBase}")
	String resourceUriBase;
	
	RestOperations restTemplate = new RestTemplate();
	
	
	@Override
	public List<Customer> allCustomers() {
		
		resourceUri = resourceUriBase + "/customerList";
		
		CustomerList customerResponse =
		        restTemplate.getForObject(resourceUri, CustomerList.class);
		           
		List<Customer> list = customerResponse.getCustomers();
			
		for (Customer customer : list) {
			System.out.println(customer);
		}
	
		return list;
	}


	@Override
	public URI createCustomer(Customer customer) {
		
		resourceUri = resourceUriBase + "/createCustomer";
			
		try {
			ResponseEntity<Customer> response = restTemplate.postForEntity(resourceUri, customer, Customer.class);
		
			HttpStatus status =	response.getStatusCode();
			
			if (status == HttpStatus.OK) {
			
				HttpHeaders headers = response.getHeaders();
			
				URI uri = headers.getLocation();
			
				return uri;
			} else {
				throw new DuplicateCustomerException();
			}
		} catch (HttpStatusCodeException e) {	
			if (e.getStatusCode() == HttpStatus.NOT_ACCEPTABLE) {
				throw new DuplicateCustomerException();
			} else {
				throw new RuntimeException();
			}
		}
	}


	@Override
	public Customer getCustomer(long id) {
		
		resourceUri = resourceUriBase + "/customer/" + id;
					
		HttpHeaders headers = new HttpHeaders();
		
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_JSON);
		headers.setAccept(list);
		
		HttpEntity<Customer> request = new HttpEntity<>(null, headers);
				
		try {
			ResponseEntity<Customer> response = restTemplate.exchange(
				resourceUri, HttpMethod.GET, request, Customer.class);
		
			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else {
				throw new RuntimeException("Error");
			}	
		} catch (HttpStatusCodeException e) {			
    		if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
    			throw new CustomerNotFoundException();
    		} else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED 
    								|| e.getStatusCode() == HttpStatus.FORBIDDEN) {
    			throw new UnauthorizedException();
    		} else {
    			throw new RuntimeException();	
    		}
    			
		}
		
	}


	@Override
	public void updateCustomer(Customer customer) {
		
		resourceUri = resourceUriBase + "/customer/" + customer.getId();
						
		try {
			restTemplate.put(resourceUri, customer);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.UNAUTHORIZED 
					|| e.getStatusCode() == HttpStatus.FORBIDDEN) {
				throw new CustomerAccessDeniedException();
			} else {
				throw new RuntimeException();
			}
		}
		
	}


	@Override
	public void deleteCustomer(long id) {
		
		resourceUri = resourceUriBase + "/customer/" + id;

		try {
			restTemplate.delete(resourceUri);
		
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new CustomerNotFoundException();
			}
			
		}
		
	}

}
