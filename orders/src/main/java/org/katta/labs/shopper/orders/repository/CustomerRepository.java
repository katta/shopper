package org.katta.labs.shopper.orders.repository;

import org.katta.labs.shopper.orders.domain.CustomerView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Repository
public class CustomerRepository {

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    private RestTemplate restTemplate;

    public CustomerRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerView findCustomer(String customerId) {
        return restTemplate.getForObject(String.format("%s/%s", customerServiceUrl, customerId), CustomerView.class);
    }

/*
    @JmsListener(destination = "${activemq.queue.name}", containerFactory = "containerFactory")
    public void customerCreated(String customerJson) {
        System.out.println(CustomerView.fromJson(customerJson));
    }
*/


}
