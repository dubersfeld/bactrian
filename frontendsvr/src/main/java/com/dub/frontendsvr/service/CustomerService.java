package com.dub.frontendsvr.service;

import java.net.URI;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.frontendsvr.domain.Customer;

@PreAuthorize("hasAuthority('VIEW')")
public interface CustomerService {
	
	public List<Customer> allCustomers();
	
	public Customer getCustomer(long id);
	
	@PreAuthorize("hasAuthority('CREATE')")
	public URI createCustomer(Customer customer);
	
	@PreAuthorize("hasAuthority('UPDATE')")
	public void updateCustomer(Customer customer);
	
	@PreAuthorize("hasAuthority('DELETE')")
	public void deleteCustomer(long id);
	
	
}
