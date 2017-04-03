package org.katta.labs.shopper.orders.service;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.katta.labs.shopper.orders.domain.CustomerView;
import org.katta.labs.shopper.orders.domain.Order;
import org.katta.labs.shopper.orders.repository.CustomerRepository;
import org.katta.labs.shopper.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private JmsTemplate jmsTemplate;

    @Value("${activemq.queue.name}")
    private String queueName;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, JmsTemplate jmsTemplate) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public String create(Order order) {
        CustomerView customer = customerRepository.findCustomer(order.getCustomerId());
        order.attachCustomer(customer);

        Order savedOrder = orderRepository.save(order);

        jmsTemplate.send(queueName, session -> {
            ActiveMQTextMessage message = new ActiveMQTextMessage();
            message.setText(order.toJson());
            return message;
        });

        return savedOrder.getId();
    }
}
