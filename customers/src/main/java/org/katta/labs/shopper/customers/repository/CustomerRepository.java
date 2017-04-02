package org.katta.labs.shopper.customers.repository;

import org.katta.labs.shopper.customers.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByName(String name);
}
