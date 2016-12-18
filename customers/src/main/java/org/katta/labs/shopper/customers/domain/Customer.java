package org.katta.labs.shopper.customers.domain;

import java.util.UUID;

public class Customer {
    private String name;
    private String id;

    public Customer(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }
}
