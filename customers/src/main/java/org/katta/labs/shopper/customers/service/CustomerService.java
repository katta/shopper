package org.katta.labs.shopper.customers.service;

import org.katta.labs.shopper.customers.config.JmsConfig;
import org.katta.labs.shopper.customers.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private JmsTemplate jmsTemplate;
    private JmsConfig jmsConfig;

    @Autowired
    public CustomerService(JmsTemplate jmsTemplate, JmsConfig jmsConfig) {
        this.jmsTemplate = jmsTemplate;
        this.jmsConfig = jmsConfig;
    }

    public String create(Customer customer) {
        jmsTemplate.convertAndSend(jmsConfig.getQueueName(), customer);
        return customer.getId();
    }
}
