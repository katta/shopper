package org.katta.labs.shopper.customers.service;

import org.katta.labs.shopper.customers.config.Config;
import org.katta.labs.shopper.customers.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private JmsTemplate jmsTemplate;
    private Config config;

    @Autowired
    public CustomerService(JmsTemplate jmsTemplate, Config config) {
        this.jmsTemplate = jmsTemplate;
        this.config = config;
    }

    public String create(Customer customer) {
        jmsTemplate.convertAndSend(config.getQueueName(), customer.toJson());
        return customer.getId();
    }
}
