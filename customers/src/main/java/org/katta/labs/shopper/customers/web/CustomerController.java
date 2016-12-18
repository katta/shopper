package org.katta.labs.shopper.customers.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
