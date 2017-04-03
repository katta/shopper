package org.katta.labs.shopper.orders.service;

import org.katta.labs.shopper.orders.domain.CustomerView;
import org.katta.labs.shopper.orders.domain.Order;
import org.katta.labs.shopper.orders.repository.CustomerRepository;
import org.katta.labs.shopper.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public String create(Order order) {
        CustomerView customer = customerRepository.findCustomer(order.getCustomerId());
        order.attachCustomer(customer);
        return orderRepository.save(order).getId();
    }
}
