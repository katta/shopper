package org.katta.labs.shopper.customers.web;

import org.katta.labs.shopper.customers.domain.Customer;
import org.katta.labs.shopper.customers.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{customerId}")
    public Customer getById(@PathVariable String customerId) {
        return customerService.get(customerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createCustomer(@RequestBody Customer customer) {
        String customerId = customerService.create(customer);
        return Collections.singletonMap("customerId", customerId);
    }
}
