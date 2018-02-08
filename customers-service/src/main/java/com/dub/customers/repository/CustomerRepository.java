package com.dub.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dub.customers.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
