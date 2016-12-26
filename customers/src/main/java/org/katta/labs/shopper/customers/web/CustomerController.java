package org.katta.labs.shopper.customers.web;

import org.katta.labs.shopper.customers.domain.Customer;
import org.katta.labs.shopper.customers.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Customers resource";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }
}
