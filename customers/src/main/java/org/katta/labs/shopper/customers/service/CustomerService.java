package org.katta.labs.shopper.customers.service;

import org.katta.labs.shopper.customers.domain.Customer;
import org.katta.labs.shopper.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String create(Customer customer) {
        return repository.save(customer).getId();
    }
}
