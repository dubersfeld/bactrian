package com.dub.customers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dub.customers.exceptions.CustomerNotFoundException;
import com.dub.customers.exceptions.DuplicateCustomerException;
import com.dub.customers.model.Customer;
import com.dub.customers.model.CustomerList;
import com.dub.customers.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
		
	
	@Override
	public CustomerList getCustomerList() {
		
		CustomerList customers 
				= new CustomerList(customerRepository.findAll());
		return customers;
	}


	@Override
	public Customer createCustomer(Customer customer) {
		
		try {
			return customerRepository.save(customer);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateCustomerException();
		}
	}


	@Override
	public Customer getCustomerById(long id) {
	
		Customer customer = customerRepository.findOne(id);
				
		// never return a null object
		if (customer != null) {
			return customer;
		} else {
			throw new CustomerNotFoundException();
		}
			
	}


	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}


	@Override
	public void deleteCustomer(long id) {
			
		customerRepository.delete(id);
	}
}
