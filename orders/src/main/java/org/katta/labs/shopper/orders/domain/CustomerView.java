package org.katta.labs.shopper.orders.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomerView {
    private String id;
    private String name;

    private CustomerView() {
    }

    public CustomerView(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CustomerView{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static CustomerView fromJson(String customerJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(customerJson, CustomerView.class);
        } catch (IOException e) {
            throw new RuntimeException("Error serializing", e);
        }

    }
}
