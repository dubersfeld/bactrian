package com.dub.customers.services;

import com.dub.customers.model.Customer;
import com.dub.customers.model.CustomerList;

public interface CustomerService {

	public CustomerList getCustomerList();
	
	public Customer getCustomerById(long id);
	
	public Customer createCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public void deleteCustomer(long id);
}
