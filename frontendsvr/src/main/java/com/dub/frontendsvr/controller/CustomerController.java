package com.dub.frontendsvr.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dub.frontendsvr.domain.Customer;
import com.dub.frontendsvr.exceptions.CustomerAccessDeniedException;
import com.dub.frontendsvr.exceptions.CustomerNotFoundException;
import com.dub.frontendsvr.exceptions.DuplicateCustomerException;
import com.dub.frontendsvr.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/allCustomers",
								method = RequestMethod.GET)
	public ModelAndView allCustomers() {
		
		List<Customer> customers 
							= customerService.allCustomers();
		
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("customers", customers);
		
				
		return new ModelAndView("customers/allCustomers", params);
	}	
		
	@RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
	public ModelAndView createCustomer(ModelMap model) {
		model.addAttribute("customerForm", new CustomerForm());
		
		return new ModelAndView("customers/createCustomer", model);
	}
	
	@RequestMapping(value = "createCustomer")
	public String createCustomer(
			@Valid @ModelAttribute("customerForm") CustomerForm form,
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "customers/createCustomer";
		} else {
			Customer customer = new Customer();
			customer.setFirstName(form.getFirstName());
			customer.setLastName(form.getLastName());
			customer.setCity(form.getCity());
			
			try {
				URI newCustomer = customerService.createCustomer(customer);	
				model.addAttribute("uri", newCustomer.toString());
				return "customers/createCustomerSuccess";
			} catch (DuplicateCustomerException e) {
				result.rejectValue("firstName", "duplicate", "name already present");				
				return "customers/createCustomer";
			} catch (AccessDeniedException e) {
				return "unauthorized";
			}
		}
	}
	

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.GET)
	public ModelAndView updateCustomer(ModelMap model) {
		model.addAttribute("getCustomer", new CustomerIdForm());
		
		return new ModelAndView("customers/updateCustomer1", model);
	}
	
	@RequestMapping(value = "/updateCustomer1", method = RequestMethod.POST)
	public String updateCustomer(
				@Valid @ModelAttribute("getCustomer") CustomerIdForm form,
				BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
	
			return "customers/updateCustomer1";
		} else {
			try {
				long customerId = form.getId();
				Customer customer = customerService.getCustomer(customerId);
				model.addAttribute("customer", customer);
				return "customers/updateCustomer2";
			} catch (CustomerNotFoundException e) {
				result.rejectValue("id", "notFound", "customer not found");							
				return "customers/updateCustomer1";
			} catch (RuntimeException e) {
				return "error";
			}// try
		}// if
		
	}
	

	@RequestMapping(value = "/updateCustomer2", 
							method = RequestMethod.POST)
	public String updateCustomer2(
						@Valid @ModelAttribute("customer") CustomerUpdateForm form,
						BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "customers/updateCustomer2";			
		} else {
			try {
				Customer customer = new Customer();
				customer.setFirstName(form.getFirstName());
				customer.setLastName(form.getLastName());
				customer.setCity(form.getCity());
				customer.setId(form.getId());
				
				customerService.updateCustomer(customer);			
				return "customers/updateCustomerSuccess";
			} catch (CustomerAccessDeniedException e) {
				return "customers/accessDenied";
			} catch (AccessDeniedException e) {
				return "unauthorized";
			} catch (RuntimeException e) {
				return "error";
			}				
		}// if		
	}

	
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
	public ModelAndView deleteCustomer(ModelMap model) {
		model.addAttribute("getCustomer", new CustomerIdForm());
		
		return new ModelAndView("customers/deleteCustomer", model);
	}
	
	
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public String deleteCustomer(
				@Valid @ModelAttribute("getCustomer") CustomerIdForm form,
				BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			return "customers/deleteCustomer";
		} else {
			try {
				
				long customerId = form.getId();
				Customer customer = customerService.getCustomer(customerId);
				model.addAttribute("customer", customer);
				customerService.deleteCustomer(customerId);
				return "customers/deleteCustomerSuccess";
			} catch (CustomerNotFoundException e) {
				result.rejectValue("id", "notFound", "customer not found");							
				return "customers/deleteCustomer";
			} catch (AccessDeniedException e) {
				return "unauthorized";
			} catch (RuntimeException e) {
				return "error";
			}
		}
	}
}
