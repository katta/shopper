package org.katta.labs.shopper.orders.repository;

import org.katta.labs.shopper.orders.domain.CustomerView;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    @JmsListener(destination = "${activemq.queue.name}", containerFactory = "containerFactory")
    public void customerCreated(String customerJson) {
        System.out.println(CustomerView.fromJson(customerJson));
    }
}
